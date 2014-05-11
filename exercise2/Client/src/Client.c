//* client.c */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <ctype.h>
#include <PDU.h>

#define PORT 8080
#define BUFFER_SIZE 1024

/** prints error message and terminates program */
static void error_exit(char *errorMessage);

/** prints ASN.1 BER encoded pdu within buffer to stdout */
static void print_pdu_buffer_hex(char *buffer, size_t length);

/** prints ASN.1 BER decoded pdu to stdout */
static void print_pdu_decoded(PDU_t *pdu);

#define NONE 	0
#define DEC 	1
#define ENC 	2

int main(int argc, char *argv[]) {
	// ##### handle arguments #####
	if (argc < 3 || argc > 4) {
		error_exit(
				"Not enough or too many arguments given\nUsage: Client <IP> <integer> [-dec | -enc]");
	}
	int printOption = NONE;
	if (argc == 4) {
		// optional argument found
		if (strcmp(argv[3], "-dec") == 0) {
			printOption = DEC;
		} else if (strcmp(argv[3], "-enc") == 0) {
			printOption = ENC;
		} else {
			error_exit(
					"Unknown argument given.\nUsage: Client <IP> <integer> [-dec | -enc]");
		}
	}
	int value = atoi(argv[2]);


	// ##### set up socket communication #####

	// create socket
	int sock;
	if ((sock = socket( AF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0) {
		error_exit("Failed to create socket.");
	}
	// create address of server (IP and port)
	struct sockaddr_in server;
	memset(&server, 0, sizeof(server));
	unsigned long addr;
	if ((addr = inet_addr(argv[1])) != INADDR_NONE) {
		// argv[1] is IP address
		memcpy((char *) &server.sin_addr, &addr, sizeof(addr));
	} else {
		// convert hostname (e.g. localhost) to IP address
		struct hostent *host_info;
		host_info = gethostbyname(argv[1]);
		if (host_info == NULL) {
			error_exit("Failed to resolve server's IP address.");
		}
		memcpy((char *) &server.sin_addr, host_info->h_addr, host_info->h_length );
	}
	// set IPv4 connection type and port
	server.sin_family = AF_INET;
	server.sin_port = htons( PORT);
	// connect to server
	if (connect(sock, (struct sockaddr*) &server, sizeof(server)) < 0)
		error_exit("Failed to establish connection to server.");


	// ##### start communication #####

	// create request message
	PDU_t *pdu_request;
	asn_enc_rval_t enc; // encoder return value
	// allocate pdu
	pdu_request = calloc(1, sizeof(PDU_t));
	if (!pdu_request) {
		perror("calloc() failed");
		exit(1);
	}
	// fill request data
	pdu_request->choice.request.value = value;
	pdu_request->present = PDU_PR_request;
	// encode pdu to buffer
	char buffer[BUFFER_SIZE];
	enc = der_encode_to_buffer(&asn_DEF_PDU, pdu_request, buffer,
			sizeof(buffer));
	if (enc.encoded == -1) {
		fprintf(stderr, "Failed to encode PDU (at %s)\n",
				enc.failed_type ? enc.failed_type->name : "unknown");
		exit(1);
	}

	// send request to server
	if ((write(sock, buffer, strlen(buffer))) == -1) {
		error_exit("Failed to send value to server.");
	}
	printf("sent : %d", value);
	switch (printOption) {
	case ENC:
		print_pdu_buffer_hex(buffer, enc.encoded);
		break;
	case DEC:
		print_pdu_decoded(pdu_request);
		break;
	default:
		printf("\n");
		break;
	}

	// wait for answer
	int bytesRead = (int) read(sock, buffer, BUFFER_SIZE);
	if (bytesRead == -1) {
		error_exit("Failed to receive response from server.");
	}
	PDU_t *pdu_resp = 0;
	asn_dec_rval_t dec; // decoder return value
	dec = ber_decode(0, &asn_DEF_PDU, (void **) &pdu_resp, buffer,
			sizeof(buffer));
	if (dec.consumed == -1) {
		fprintf(stderr, "Failed to decode PDU (at %s)\n",
				enc.failed_type ? enc.failed_type->name : "unknown");
		exit(1);
	}

	printf("rcvd : %li, %s", pdu_resp->choice.response.value,
			pdu_resp->choice.response.randStr.buf);
	switch (printOption) {
	case ENC:
		print_pdu_buffer_hex(buffer, dec.consumed);
		break;
	case DEC:
		print_pdu_decoded(pdu_resp);
		break;
	default:
		printf("\n");
		break;
	}

	// extract string and change upper case letters to lower case letters and vica-versa
	uint8_t *randomString = pdu_resp->choice.response.randStr.buf;
	int i;
	for (i = 0; i < pdu_resp->choice.response.randStr.size; i++) {
		if (islower(randomString[i])) {
			randomString[i] = toupper(randomString[i]);
		} else if (isupper(randomString[i])) {
			randomString[i] = tolower(randomString[i]);
		}
	}
	// pdu_resp now contains swapped random string, we can send it back to server
	enc = der_encode_to_buffer(&asn_DEF_PDU, pdu_resp, buffer, sizeof(buffer));
	if (write(sock, buffer, sizeof(buffer)) == -1) {
		error_exit("Failed to send value to server.");
	}
	printf("sent: %li, %s ", pdu_resp->choice.response.value,
			pdu_resp->choice.response.randStr.buf);
	switch (printOption) {
	case ENC:
		print_pdu_buffer_hex(buffer, enc.encoded);
		break;
	case DEC:
		print_pdu_decoded(pdu_resp);
		break;
	default:
		printf("\n");
		break;
	}

	// wait for FAIL / OK
	bytesRead = (int) read(sock, buffer, BUFFER_SIZE);
	PDU_t *pdu_ver = 0;
	dec = ber_decode(0, &asn_DEF_PDU, (void **) &pdu_ver, buffer,
			sizeof(buffer));
	if (pdu_ver->choice.verification.result == result_ok) {
		printf("rcvd : OK");
	} else if (pdu_ver->choice.verification.result == result_fail) {
		printf("rcvd : FAIL");
	}
	switch (printOption) {
	case ENC:
		print_pdu_buffer_hex(buffer, dec.consumed);
		break;
	case DEC:
		print_pdu_decoded(pdu_ver);
		break;
	default:
		printf("\n");
		break;
	}

	/* terminate connection */
	close(sock);
	exit(0);
}

static void error_exit(char *errorMessage) {
	fprintf(stderr, "%s: %s\n", errorMessage, strerror(errno));
	exit(EXIT_FAILURE);
}

static void print_pdu_buffer_hex(char *buffer, size_t length) {
	int i;
	fprintf(stdout, "\n [enc:");
	for (i = 0; i < length; i++) {
		fprintf(stdout, " %02hhX", buffer[i]);
	}
	fprintf(stdout, "]\n");
}

static void print_pdu_decoded(PDU_t *pdu) {
	if (pdu->present == PDU_PR_request) {
		fprintf(stdout, "\n [dec: PDU ::= request : { value %li }]\n",
				pdu->choice.request.value);
	} else if (pdu->present == PDU_PR_response) {
		fprintf(stdout,
				"\n [dec: PDU ::= response : { value %li , randStr \"%s\"}]\n",
				pdu->choice.response.value, pdu->choice.response.randStr.buf);
	} else if (pdu->present == PDU_PR_verification) {
		fprintf(stdout, "\n [dec: PDU ::= verification : { result %li }]\n",
				pdu->choice.verification.result);
	}
}

