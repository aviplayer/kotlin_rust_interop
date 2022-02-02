#include <stdarg.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdlib.h>

typedef struct KRouter {
  char *method;
  char *uri;
  char *(*handler)(char*);
} KRouter;

void start_rust_server(char *raw_addr, struct KRouter *kroutes, uint32_t length);
