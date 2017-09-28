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

-----

## [Przykład 6] : 'Wyjątkowy' BP ;)
Kod: `P6_Exception_BP_MiniGUI`

Kolejne kroki ćwiczenia:
- Uruchom przykład; klikaj w przycisk; spróbuj przewidzieć kiedy wystąpi błąd; nie przewidzisz :)
- W widoku *Breakpoints* dodaj *Exception Breakpoint*; użyj przycisku ![](img/bp_exception.png)
  - w okienku znajdź *"szeroką"* klasę wyjątków tj. `java.lang.Exception`; zaznacz reagowanie na  wyjątki *caught* i *uncaught*
  - dodatkowo, w widoku *Breakpoints*, w dodatkowych sutawieniach nowego BP zaznacz *Subcalsses of this exception*
- Uruchom kod w trybie debuggowania
  - znudź się przeklikiwaniem wystąpień kolejnych, nieistotnych dla nas, wyjątków
  - przerwij debuggowanie
- W *Breakpoints* -> *PPM* na naszym *BP* -> *Breakpoint Properties* -> *Filtering*
  - w okienku zaweź reagowanie na wyjątki tylko do pakietu `p6`
  - uruchom ponownie debuggowanie
  - klikaj przycisk aż trafisz na wyjątek; na podstawie stosu ramek w *Debug* znajdziesz miejsce wystepienia wyjątku (Eclipse najprawdopodobniej przeniesie Cię też do odpowiedniej lini kodu)
- Zakończ debuggowanie

-----

## [Przykład 7] : Warunkowe BP
Kod: `P7_Warunkowy_BP_ProcessingLoop`

> Wyobraź sobie, że chcesz przeanalizować kod który jest wywoływany mnóswo razy; Ciebie interesuje tylko jedno z wywołań - dla konkretnych argumentów/zmiennych...

Kolejne kroki ćwiczenia:
- Uruchom przykład; spróbuj przeanalizować kod
- Dodaj BP w metodzie `main` , wewnątrz pętli - w linii `p.process(inst);`
- Spróbuj, używając podglądu zmiennych i przycisku *Resume* *"zapolować"* na wywołanie metody `process(..)` dla obiektu klasy `Installment` z polem `amount` równym `123` oraz polem `type` równym `InstallmentType.VARIABLE`
- Stwierdź, że jest to mozolne
- Przerwij debuggowanie
- Znajdź dodany BP w widoku *Berakpoints* -> *PPM* -> Zaznacz checkbox *Conditional* i w polu poniżej wpisz warunek, który ewaluuje się do `true`/`false`:
  ```
  inst.amount == 123 && inst.type.equals(p7.repos.InstallmentType.VARIABLE)
  ```
- Zapisz zmiany (*Apply and Close*)
- Ponownie uruchom debuggowanie; gdy debugger się zatrzyma - sprawdź czy zgadzają się warunki zatrzymania
- Zakończ debuggowanie

## [Przykład 8] : Połączenie zdalne do działającej JVM
Kod: `P8_Worker` oraz `P8_Helper`

> Ćwiczenie będzie składało się z dwóch faz. Pierwszą z nich będzie uruchomienie "z palca" przygotowanego kodu na JVM **nie**zarządzanej przez Eclipse. Drugim krokiem będzie połączenie się z tą JVM z Eclipse-a. Kulminacją będzie próba debuggowania zdalnej JVM z Eclipse-m jako klientem-debuggerem.

### Krok 1 : Uruchomienie kodu na osobnej JVM
- Zapisz sobie gdzieś źródła przykładów `P8_Worker` oraz `P8_Helper`
- Oba projekty zainstalluj do swojego lokalnego repozyteorium używając polecenia `mvn clean install` w lini komend; ważne jest to aby projekty trafiły do Twojego lokalnego repozytorium oraz aby ich "jar"-y trafiły do podkatalogów */target*
- Do osobnego folderu skopiuj wspomniane *jar*-y z katalogów */target* obu projektów
- Zmień ich nazwy na coś prostszego: *worker.jar* oraz *helper.jar*
- W lini poleceń, będąc w katalogu z *jar*-ami wykonaj polecenie: `java -cp worker.jar:helper.jar p8.worker.Worker`
- Powinno dojść do uruchomienia programu sumującego niewielkie losowe liczby całkowite
- Zakończ działanie aplikacji

### Krok 2 : Uruchomienie kodu na osobnej JVM przygotowanej do zdalnego debuggowania
- Uruchom kod z przygotowanych *jar*-ów podobnie jak poprzedni ale dodaj parametr, który sprawi, że JVM będzie debuggowalna i będzie czekać na połączenia na wskazanym porcie (**36203**); słowem - uruchom: 
  ```
  java -agentlib:jdwp=transport=dt_socket,suspend=n,server=y,address=36203 -cp worker.jar:helper.jar p8.worker.Worker
  ```
- Nie zamykaj lini poleceń; niech aplikacja cały czas działa!

### Krok 3 : Połączenie się do JVM z Eclipse
- Przygotuj pusty workspace w Eclipse; dodaj tam pusty projekt mavenowy
- Przejdź do perspektywy *Debbug*
- Rozwiń opcje przy przycisku *Debug As* -> *Debug Configurations* -> Dodaj nowy element w kategorii *Remote Java Application*; skonfiguruj go:
  - 













