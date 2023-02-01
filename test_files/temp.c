#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <math.h>
#include <unistd.h>
#define SIZE_STRING 200

void nl_flush() {
	char c;
	while ((c = getchar()) != '\n' && c != EOF) { }
}

char *str_concat(char *s1, char *s2) {
	char *buf = malloc(sizeof(char) * SIZE_STRING);
	strcat(buf,s1);
	strcat(buf,s2);
	return buf;
}


float  __nl__sommac(int __nl__a, int __nl__d, float __nl__b, char *__nl__size);
int __nl__c = 1;

void __nl__stampa(char *__nl__messaggio);

void __nl__esercizio();


float  __nl__sommac(int __nl__a, int __nl__d, float __nl__b, char *__nl__size) {
float __nl__result;
__nl__result = __nl__a + __nl__b + __nl__c + __nl__d;
if (__nl__result > 100) {
char __nl__valore[200];
strcpy(__nl__valore, "grande");
strcpy(__nl__size, __nl__valore);
} else {
char __nl__valore[200];
strcpy(__nl__valore, "piccola");
strcpy(__nl__size, __nl__valore);
}
return __nl__result;
}

void __nl__stampa(char *__nl__messaggio) {
int __nl__a;
int __nl__i;
for (int __nl__x = 4; __nl__x <= 1; __nl__x++) {
printf("\n");
}
printf("%s\n", __nl__messaggio);
}


void __nl__esercizio() {
int __nl__a = 1;
float __nl__b = 2.2;
int __nl__x = 3;
char __nl__taglia[200];
char __nl__ans1[200];
char __nl__ans[200];
strcpy(__nl__ans, "no");
float __nl__risultato = __nl__sommac(__nl__a, __nl__x, __nl__b, __nl__taglia);
__nl__stampa("la somma  incrementata  è ");
printf("%s\n", __nl__taglia);
__nl__stampa(" ed è pari a ");
printf("%f\n", __nl__risultato);
printf("vuoi continuare? (si/no) - inserisci due volte la risposta\n");
fgets(__nl__ans, 200, stdin);
__nl__ans[strcspn(__nl__ans, "\n\r")] = 0;
fgets(__nl__ans1, 200, stdin);
__nl__ans1[strcspn(__nl__ans1, "\n\r")] = 0;
while (strcmp(__nl__ans, "si") == 0) {
printf("inserisci un intero:");
scanf("%d" , &__nl__a);
nl_flush();
	printf("inserisci un reale:");
scanf("%f" , &__nl__b);
nl_flush();
	__nl__risultato = __nl__sommac(__nl__a, __nl__x, __nl__b, __nl__taglia);
__nl__stampa("la somma  incrementata  è ");
printf("%s\n", __nl__taglia);
__nl__stampa(" ed è pari a ");
printf("%f\n", __nl__risultato);
printf("vuoi continuare? (si/no):");
fgets(__nl__ans, 200, stdin);
__nl__ans[strcspn(__nl__ans, "\n\r")] = 0;
}
printf("\n");
printf("ciao");
}

int main() {
	
	__nl__esercizio();
}