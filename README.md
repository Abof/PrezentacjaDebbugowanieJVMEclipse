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

-----

## [Przykład 4] : Modyfikacja zmiennych
Kod: `P4_Modyfikacja_Zmiennych_Lottery`

Tło przykładu:
> Kod przedstawia loterię losującą klienta; dla jego rat zostaną zastosowana promocja; porównanie sum rat przed i po modufikacji wyświetlane jest pod koniec działania

> Wyobraź sobie, że *"biznes"* ma zastrzeżenia do działania aplikacji. Wg nich dla klienta nr **13** pod koniec działania mechanizmu zostały wyliczone niepoprawne raty.

Kolejne kroki ćwiczenia:
- Spróbuj uruchomić aplikację; próba zakończy się fiaskiem - nie mamy dostępu do odpowiedniej grupy
- Uruchom aplikację ale w trybie debuggowania; przejdź do metody `makeDreamsComeTrue()`; wewnątrz jej:
  - Przejdź do `checkIfOprInGroup()` - do linijki gdzie zmienna `oprCtx` jest już zainicjalizowana ale nie doszło do sprawdzenia grupy dostępowej
  - podmień `oprCtx` na nowy obiekt; znajdź zmienną w *Variables* -> *PPM* -> *Change Value*; w okienku wpisz kod, który stworzy nowy obiekt: `new OperatorCtx("Haker", java.util.Arrays.asList(1234));`; wróć do metody `makeDreamsComeTrue()`
  - gdy wartość zmiennej `winnerId` zostanie już ustalona - jak najszybciej zmień jej wartość na `13`!
  - podobnie jak dla grupy dostępowej, gdy zostanie ustalony wynik sprawdzania ograniczeń dla klienta - zmienna `results` - podmień jej wartość; tym razem w okienku dodaj kod przygotowujący i zmieniający obiekt do podmiany:
     ```
      CheckingResult newResult = new CheckingResult();
      newResult.type = p4.restriction.CheckingResult.ResultType.NO_RESTRICTIONS;
      return newResult;
      ```
   - jak tylko lista rat klienta zostanie pobrana - zmienna `installmentsForUser` - zauważ w widoku *Variables*, że zawiera ona `null`-e; dostań się do nich i podmień je używając konstruktora `new Integer(0)`
   - w konstrukcji `for` pod koniec metody dodaj stosowne *expressions* i spróbuj dojść do tego w czym tkwi problem zgłaszany przez *"bizes"*
- Zakończ debuggowanie

-----

## [Przykład 5] : Uruchamianie kodu + force return
Kod: `P4_Modyfikacja_Zmiennych_Lottery`

Upewnij się, że:
- wykonałeś poprzednie ćwiczenie - teraz czas na przejście przez ten sam kod ale z użyciem innych mechanizmów
- w perspektywnie *Debug* masz dodany widok *Display*

Kolejne kroki ćwiczenia:
- Przejdź do wnętrza metody `makeDreamsComeTrue()`
- Przejdź do wnętrza metody `checkIfOprInGroup()`
  - po zaincjalizowaniu `oprCtx` a przed `if`-em - przetestuj poprzez ewaluację w widoku *Debug* czy jestesś w odpowienidj grupie; przekopiuj warunek z `if`-a do *Display*; zaznacz kod i do ewaluuj przyciskiem ![](img/display_eval.png)
  - dodaj odpowiednią grupę do kontekstu poprzez wykonanie kodu w *Display*; poniższy kod wklej, zaznacz i wykonaj przyciskiem ![](img/display_exec.png) (zwróć uwagę na testowe *"sysouty"*):
    ```
    System.out.println(oprCtx.getGroups());
    oprCtx.getGroups().add(1234);
    System.out.println(oprCtx.getGroups());
    ```
  - wróć do metody `makeDreamsComeTrue()`
- Wywołaj metodę *"z innej beczki*" - w *Display* ewaluuj kilkukrotnie np. `drawUserId()`
- Wejdź do pierwszej linii metody `drawUserId()` w momencie inicjalizowania zmiennej `winnerId`
  - wymuś natychmiastowe zwrócenie wartości `12` **!** poprzez wpisanie jej w *Display* zaznaczeniu, *PPM* -> *Force Return*
- Podobnie - wejdź do pierwszej linii `checkForUser(..)` i ponownie wymuś natychmiastowe zwrócenie wyniku; tym razem jest to obiekt; posiłkując się kodem z metody, w *Display*, przygotuj obiekt a na końcu zwróć ją przez `return`; zaznacz kod, *PPM* -> *Force Return*; kod:
    ```
    CheckingResult cr = new CheckingResult();
    cr.type = NO_RESTRICTIONS;
    return cr;
    ```
- Kontynnuj debuggowanie aż do momentu przypisania listy rat do zmiennej `installmentsForUser`; zwróć uwagę, że lista zawiera `null`-e
- W widoku *Display* wykonaj *testowy* kod modyfikujący, w ramach osobnej listy, kolkcję rat:
  ```
  List<Integer> correctList = new java.util.ArrayList<>();
  for (Integer inst : installmentsForUser) {
    correctList.add( inst == null ? new Integer(0) : inst );
  };
  System.out.println(correctList);
  ```
- Jeżeli powyższe wykonanie tworzy poprawną listę dodaj do kodu linię podmieniającą listy `installmentsForUser = correctList;` ; uruchom kod; kontynuuj debuggowanie
- Zakończ debuggowanie








