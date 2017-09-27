# PrezentacjaDebbugowanieJVMEclipse
Prezentację znajdziesz tu: https://abof.github.io/PrezentacjaDebbugowanieJVMEclipse
## Opis przykładów
:heavy_check_mark: Kod źródłowy do przykładów znajdziesz w folderze `src_przykladow`. Każdy z nich jest maven-owym projektem, który można bez problemów zaimportować do Eclipse.

:heavy_check_mark: Przed przeprowadzeniem każdego z ćwiczeń zresetuj perspektywę do debuggowania :  usuń poprzednio stworzone breakpointy (BP) itd.

:heavy_check_mark: Przed przejściem do debuggowania uruchom *"normalnie"* każdą z aplikacji (najlepiej kilkukrotnie; szukaj klas z `Main` w nazwie). Zobaczysz jak działają oraz dodatkowo pod przyciskiem uruchamiającym debuggowanie pojawi się skrót do, przed chwilą uruchomionej, aplikacji.

:heavy_check_mark: Przeczytaj wysztkie kroki danego ćwiczenia przed przystąpieniem do jego realizacji.

-----

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
- Zakończ debuggowanie

-----

## [Przykład 2] : Przegląd stosu ramek + Cofanie się do początku ramki (drop to frame)
Kod: `P1_Podstawy_Nawigacji_Words`

Kolejne kroki ćwiczenia:
- Przeglądanie ramek
  - Postępuj jak w *Przykładzie 1* ze zwracaniem szczególnej uwagi na widok *Debug* oraz stos ramek 
  - Gdy ramek będzie sporo - spróbuj się pomiędzy nimi poprzełączać zwracając uwagę na widok *Variables*
- Użycie *Drop to frame*
  - Dodaj BP na wejściu `WordsProvider.provide()`; pozostałe BP wyłącz; uruchom ponownie debuggowanie; powinieneś trafić do metody `provide()`
  - Dodaj BP wewnątrz `forEach` np. w `wordList.add(new Word(commonWord));`
  - Iteruj wewnątrz pętli używając przycisku *resume*; zrób to ok 10 razy; zwróć uwagę, że lista `wordList` zostaje uzupełniana
  - W widoku *Debug* znajdź ramkę wywołania `WordsProvider.provide()`; PPM na ramce -> *Drop to frame*; przebieg wywołania wrócił do początku wywołania metody
  - Ponownie pozwól dodać kilka elementów do listy...
  - Ponów *drop to frame* wg tego schematu kilkukrotnie; zwróć uwagę, że lista zostaje uzupełniana nadmiarowymi elementami!
  - Wycisz BP i dojdź do końca działania aplikacji; zwróć uwagę na logi potwierdzające, że efekt uboczny w postaci niepotrzebnego zasilania listy tymi samymi elementami miał miejsce.
  - Zakończ debuggowanie
-----

## [Przykład 3] : Inspekcja zmiennych
Kod: `P3_Inspekcja_Zmiennych_HelpDesk`

Upewnij się, że:
- w perspektywnie *Debug* masz widoki *Variables* oraz *Expressions*; ten drugi powinien nie mieć zdefiniowanych żadnych wyrażeń
- upewnij się, że **nie masz** zdefiniowanego *Details Formatter* dla klasy `Programmer` z przykładu

Kolejne kroki ćwiczenia:
- Uruchom kilkukrotnie `P3_MainApp_HelpDesk`; zwróć uwagę na wyniki działania w konsoli
- Spróbuj - poprzez debuggowanie step by step, począwszy od metody `main` - dostać się do metody `HelpDesk.dispatchTicket(Ticket)`; po drodze:
  - zwracaj uwagę na widok *Variables*, który prezentuje dane 
  - spróbuj *"poprzerzucać"* się pomiędzy ramkami
  - jeżeli to możliwe spróbuj wskazywać myszką w edytorze kodu zainicjalizowane zmienne - powinno dojść do wuświetlenia podglądu tych zmiennych
- będąc już w metodzie `dispatchTicket(..)`:
  - w jej ostatniej linijce dodaj BP; metoda jest wywoływana w pętli; będziemy sprawdzać kolejne wywołania tej metody
  - użyj przycisku *resume* i podglądaj istotne dane w widoku *Variables*; pamiętaj, że obiekty to *"drzewka"*, które można rozwijać; mowa o danych:
    - `ticket` -> pole `domian`
    - `wiselyChoosenProgrammer` -> `name`
    - `wiselyChoosenProgrammer` -> `mainDomain`
    - `programmerIdx`
  - ponieważ podgląd konkretnych danycj w widoku *Variables* jest mało wygodny dodaj do widoku *Expressions* poniższe wyrażenia i kontynuuj podgląd kolejnych kroków iteracji w widoku *Expressions*:
    - `ticket.domain.name`
    - `wiselyChoosenProgrammer.name + "(" + wiselyChoosenProgrammer.mainDomain + ")"`
    - `programmerIdx`
  - **Zauważ**, że występuje problem; prawdopodobnie lista programistów rośnie; zbadajmy to poprzez:
    - Zaznaczenie w edytorze jakiegokolwiek wystąpienia nazwy klasy `Progmrammer` -> *PPM* -> *InstancesCount*
    - Zaznaczenie w edytorze jakiegokolwiek wystąpienia nazwy klasy `Progmrammer` -> *PPM* -> *All Instances*
- Spróbuj znaleźć źródło problemu
- Zakończ debuggowanie

Dodatkowe kroki dot. *deatail formatter-a*:
- podczas debuggowanie; w widoku *Variables* znajdź gdzieś instancję klasy *Progmrammer*; jej podgląd to w tej chwili nieczytelny *toString*
- *PPM* na wspomnianej instancji -> *New Detail Formatter*; w okienku napisz coś w rodzaju czytelnego toStringa dla wspomnianej klasy; wyświetl nazwę programisty oraz jego główną dziedzinę
- kontynuuj debuggowanie zwracając uwagę, że w większości podglądów zmiennych instancje klasy `Programmer` są teraz wyswietlane w czytelny sposób - z użyciem wspomnianego *Detail Formattera*
- Zakończ debuggowanie
