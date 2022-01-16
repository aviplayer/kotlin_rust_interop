#include <stdarg.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdlib.h>

void start_rust_server(char *raw_addr);

void get(char *uri_raw, char *(*callback)(char*));

void post(char *uri_raw, char *(*callback)(char*));
