# PrezentacjaDebbugowanieJVMEclipse
## Opis przykładów
:heavy_check_mark: Kod źródłowy do przykładów znajdziesz w folderze `src_przykladow`. Każdy z nich jest maven-owym projektem, który można bez problemów zaimportować do Eclipse.

:heavy_check_mark: Przed przeprowadzeniem każdego z ćwiczeń zresetuj perspektywę do debuggowania :  usuń poprzednio stworzone breakpointy (BP) itd.

:heavy_check_mark: Przed przejściem do debuggowania uruchom *"normalnie"* każdą z aplikacji (najlepiej kilkukrotnie; szukaj klas z `Main` w nazwie). Zobaczysz jak działają oraz dodatkowo pod przyciskiem uruchamiającym debuggowanie pojawi się skrót do, przed chwilą uruchomionej, aplikacji.

:heavy_check_mark: Przeczytaj wysztkie kroki danego ćwiczenia przed przystąpieniem do jego realizacji.

## [Przykład 1] : Podstawowa nawigacja
Kod: `P1_Podstawy_Nawigacji_Words`

Upewnij się, że:
- masz wyłączony *step filtering* (![](img/db_step_filter.png))

Kolejne kroki ćwiczenia:
- Dodaj BP w nagłówku metody `main` w `P1_Main_App`
- Uruchom debuggowanie (przejdź do odpowiedniej perspektywy)
- Poruszając się jedynie przyciskami *step over*, *step into* oraz *step return* przejdź do metody `start`. Wewnątrz jej:
  - spróbuj *wejść* a potem *przejść przez* jeden z konstruktorów
  - spróbuj *wejść* a potem *wyjść* z wywołania `System.out.print(..)`
  - zajrzyj do `doSomeInterestingStuff()` a następnie w jak najmniejszej liczbie kroków do `damnInterestingThings()`
  - dostań się do metody wywoływanej pod koniec `main`-a - do `doItNow()`
    - spróbuj dotrzeć do kluczowych komend wewnątrz streama (będzie to mozolne i może się nie udać)
- **Przerwij debuggowanie** : poprzednie kroki miały pokazać pewne niedogodności w debuggowaniu; zaraz to naprawisz.
- Włącz *step filtering*; w ustawieniach Eclipse zapoznaj się z zakładką *Step filtering* a nastepnie zaznacz wysztkie pakiety oraz wszystkie checkboxy
- Powtórz debuggowanie z użyciem wymienionych w prezentacji udogodnień (*step filtering*, *run to line*, *step into selection*)

