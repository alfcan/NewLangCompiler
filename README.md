# NewLang Compiler

## Specifica Lessicale

|   **Token**   |         **Regex**          |
| :-----------: | :------------------------: |
|      DEF      |            def             |
|      OUT      |            out             |
|      FOR      |            for             |
|      IF       |             if             |
|     ELSE      |            else            |
|     THEN      |            then            |
|     WHILE     |           while            |
|      TO       |             to             |
|     LOOP      |            loop            |
|      VAR      |            var             |
|    INTEGER    |          integer           |
|     FLOAT     |           float            |
|    STRING     |           string           |
|    BOOLEAN    |          boolean           |
|     CHAR      |            char            |
|     VOID      |            void            |
|    RETURN     |           return           |
|      AND      |            and             |
|      OR       |             or             |
|      NOT      |            not             |
|     TRUE      |            true            |
|     FALSE     |           false            |
|     MAIN      |           start:           |
|     SEMI      |             ;              |
|     COMMA     |             ,              |
|     PIPE      |             \|             |
|     READ      |            <--             |
|     WRITE     |            -->             |
|    WRITELN    |            -->!            |
|     LPAR      |             (              |
|     RPAR      |             )              |
|     LBRAC     |             {              |
|     RBRAC     |             }              |
|     COLON     |             :              |
|    ASSIGN     |             <<             |
|     PLUS      |             +              |
|     MINUS     |             -              |
|     TIMES     |             \*             |
|      DIV      |             /              |
|      POW      |             ^              |
|  STR_CONCAT   |             &              |
|      EQ       |             =              |
|      NE       |          <> or !=          |
|      LT       |             <              |
|      LE       |             <=             |
|      GT       |             >              |
|      GE       |             >=             |
|      ID       | `[$_A-Za-z][$_A-Za-z0-9]*` |
| INTEGER_CONST |          `[0-9]+`          |
|  FLOAT_CONST  |      `[0-9]+\.[0-9]+`      |
|  CHAR_CONST   |          `\'.\'`           |
| STRING_CONST  |     `\" [. \\n ]  \"`      |

## Modifiche Progettuali

Il compilatore NewLang prevede le seguenti scelte progettuali:

- Il costrutto `for` accetta, oltre alle costanti intere come valore iniziale e finale, espressioni di tipo intero. _Come indicato nelle regole di inferenza riportate alla fine del file_.

  > for i << func (1,2) to n +10 loop { _body_ }

  - Per fare ciò abbiamo provveduto alla modifica della grammatica per il costrutto `for`. La modifica è riportata di seguito:

    > ForStat ::= FOR ID:id ASSIGN Expr:e1 TO Expr:e2 LOOP Body:body

    > {: RESULT = new ForStatOP(new Identifier(id), e1, e2, body); :};

- Nella fase di _Type Checking_ si verifica se lo statement `return` restituisce un espressione di tipo corrispondente al tipo della funzione in cui è contenuto.

- Per la generazione del codice, si è scelto di aggiungere il seguente prefisso per ogni identificatore: `__nl__`. Questa scelta è stata effettuata per evitare di creare conflitti con funzioni o costanti utilizzate dalle librerie di C.

## Regole di inferenze

- Identificatori:<br>
  <img src="https://i.ibb.co/xg1fVTp/identificatore.png">

- Costanti:<br>
  <img src="https://i.ibb.co/KG7LBJR/costanti.png">

- Istruzione if:<br>
  <img src="https://i.ibb.co/0sVJHP0/if-then.png">

- Istruzioni else:<br>
  <img src="https://i.ibb.co/mSnXqQZ/else.png">

- Istruzione while:<br>
  <img src="https://i.ibb.co/hBnHffB/while.png">

- Istruzione for:<br>
  <img src="https://i.ibb.co/fpjCLBt/for.png">

- Read:<br>
  <img src="https://i.ibb.co/9s8Zzk8/read.jpg">

- Write:<br>
  <img src="https://i.ibb.co/XZYRpNX/write.jpg">

- Blocco dichiarazione-istruzione:<br>
  <img src="https://i.ibb.co/mSRWj2N/blocco-dichiarazione-istruzione.png">

- Assegnazione:<br>
  <img src="https://i.ibb.co/7NM5kYz/assegnazione.png">

- Chiamata a funzione di tipo void:<br>
  <img src="https://i.ibb.co/CvSK96T/chiamata-senza-ritorno.png">

- Chiamata a funzione con tipo di ritorno:<br>
  <img src="https://i.ibb.co/3RGPk6B/chiamata-con-ritorno.png">

- Operazioni binarie:<br>
  <img src="https://i.ibb.co/p3ppXC2/operatori-binari.png">

- Operazioni unarie:<br>
  <img src="https://i.ibb.co/TMDnW5s/operatori-unari.png">

- Lista di espressioni:<br>
  <img src="https://i.ibb.co/W33FGJ9/lista-di-espressione.png">

<br>

### Tabella dei tipi per operazioni binarie

<img src="https://i.ibb.co/2PYcsYG/tabella-binari.png">

<br>

### Tabella dei tipi per operazioni unarie

<img src="https://i.ibb.co/5v8kkyB/tabella-unari.png">
