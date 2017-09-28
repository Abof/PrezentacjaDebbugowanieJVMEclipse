# PrezentacjaDebbugowanieJVMEclipse
Prezentację znajdziesz tu: https://abof.github.io/PrezentacjaDebbugowanieJVMEclipse

Możesz ściągnąć i rozpakować całe to repozytorium - prezentacja jest stroną statyczną - możesz wyświetlić `index.html` w ulubionej przeglądarce.

## Opis przykładów
:heavy_check_mark: Kod źródłowy do przykładów znajdziesz w folderze `src_przykladow`. Każdy z nich jest maven-owym projektem, który można bez problemów zaimportować do Eclipse.

:heavy_check_mark: Przed przeprowadzeniem każdego z ćwiczeń zresetuj perspektywę do debugowania :  usuń poprzednio stworzone breakpointy (BP) itd.

:heavy_check_mark: Przed przejściem do debugowania uruchom *"normalnie"* każdą z aplikacji (najlepiej kilkukrotnie; szukaj klas z `Main` w nazwie). Zobaczysz jak przykłady działają oraz dodatkowo, pod przyciskiem uruchamiającym debugowanie, pojawi się skrót do aplikacji, którą przed chwilą uruchomiłeś.

:heavy_check_mark: Przeczytaj wszystkie kroki danego ćwiczenia przed przystąpieniem do jego realizacji.

-----

## [Przykład 1] : Podstawowa nawigacja
Kod: `P1_Podstawy_Nawigacji_Words`

Upewnij się, że:
- masz wyłączony *step filtering* (ikonka: ![](img/db_step_filter.png))

Kolejne kroki ćwiczenia:
- Dodaj BP w nagłówku metody `main` w `P1_Main_App`.
- Uruchom debugowanie  i przejdź do odpowiedniej perspektywy.
- Poruszając się jedynie przyciskami *step over*, *step into* oraz *step return* przejdź do metody `start`. Wewnątrz jej:
  - spróbuj *wejść* a potem *przejść przez* jeden z konstruktorów
  - spróbuj *wejść* a potem *wyjść* z wywołania `System.out.print(..)`
  - zajrzyj do `doSomeInterestingStuff()`, a następnie w jak najmniejszej liczbie kroków do `damnInterestingThings()`
  - dostań się do metody wywoływanej pod koniec `main`-a czyli do `doItNow()`, a w niej:
    - spróbuj dotrzeć do kluczowych komend wewnątrz streama (będzie to mozolne i może się nie udać)
- **Przerwij debugowanie** : poprzednie kroki miały pokazać pewne niedogodności w debugowaniu; zaraz to naprawisz.
- Włącz *step filtering*; w ustawieniach Eclipse zapoznaj się z zakładką *Step Filtering*, a następnie zaznacz wszystkie pakiety oraz wszystkie checkboxy.
- Powtórz debugowanie z użyciem wymienionych w prezentacji udogodnień (*step filtering*, *run to line*, *step into selection*).
- Zakończ debugowanie.

-----

## [Przykład 2] : Przegląd stosu ramek + Cofanie się do początku ramki (drop to frame)
Kod: `P1_Podstawy_Nawigacji_Words`

Kolejne kroki ćwiczenia:
### Przeglądanie ramek
- Postępuj jak w *Przykładzie 1* ze zwracaniem szczególnej uwagi na widok *Debug* oraz stos ramek.
- Gdy ramek będzie sporo - spróbuj się pomiędzy nimi poprzełączać zwracając uwagę na widok *Variables*.
### Użycie 'Drop to frame'
- Dodaj BP na wejściu `WordsProvider.provide()`; pozostałe BP wyłącz; uruchom ponownie debugowanie; powinieneś trafić do metody `provide()`.
- Dodaj BP wewnątrz `forEach` np. w `wordList.add(new Word(commonWord));`.
- Iteruj wewnątrz pętli używając przycisku *resume*; zrób to ok 10 razy; zwróć uwagę na to, że lista `wordList` jest powiększana.
- W widoku *Debug* znajdź ramkę wywołania `WordsProvider.provide()`; PPM na ramce -> *Drop to frame*; przebieg wywołania wrócił do początku wywołania metody; zauważ, że operujemy na tym samym obiekcie `WordsProvider`, a on ma wciąż tą samą listę `wordList`.
- Ponownie pozwól dodać kilka elementów do listy...
- Ponów *drop to frame* wg tego schematu kilkukrotnie; zwróć uwagę, że lista zostaje uzupełniana nadmiarowymi elementami!
- Wycisz BP i dojdź do końca działania aplikacji; zwróć uwagę na logi potwierdzające, że efekt uboczny w postaci niepotrzebnego zasilania listy tymi samymi elementami miał miejsce.
- Zakończ debugowanie.

-----

## [Przykład 3] : Inspekcja zmiennych
Kod: `P3_Inspekcja_Zmiennych_HelpDesk`

Upewnij się, że:
- w perspektywie *Debug* masz dodane widoki *Variables* oraz *Expressions*; ten drugi powinien nie mieć zdefiniowanych żadnych wyrażeń.
- upewnij się, że **nie masz** zdefiniowanego *Details Formatter* dla klasy `Programmer`.

Kolejne kroki ćwiczenia:
- Uruchom kilkukrotnie `P3_MainApp_HelpDesk`; zwróć uwagę na wyniki działania w konsoli.
- Spróbuj - poprzez debugowanie *step by step*, począwszy od metody `main` - dostać się do metody `HelpDesk.dispatchTicket(Ticket)`; po drodze:
  - zwracaj uwagę na widok *Variables*, który prezentuje dane
  - spróbuj w pewnym momencie *"poprzerzucać"* się pomiędzy ramkami
  - jeżeli to możliwe spróbuj wskazywać myszką w edytorze kodu zainicjalizowane zmienne - powinno dojść do wyświetlenia podglądu tych zmiennych
- Będąc już w metodzie `dispatchTicket(..)`:
  - w jej ostatniej linijce dodaj BP; metoda jest wywoływana w pętli; będziemy sprawdzać kolejne wywołania tej metody
  - użyj przycisku *resume* i podglądaj istotne dane w widoku *Variables*; pamiętaj, że obiekty to *"drzewka"*, które można rozwijać; sprawdzaj poniższe wartości:
    - `ticket` -> pole `domian`
    - `wiselyChoosenProgrammer` -> pole `name`
    - `wiselyChoosenProgrammer` -> pole `mainDomain`
    - `programmerIdx`
  - ponieważ podgląd konkretnych danych w widoku *Variables* jest mało wygodny dodaj do widoku *Expressions* poniższe wyrażenia i kontynuuj podgląd kolejnych kroków iteracji w widoku *Expressions*:
    - `ticket.domain.name`
    - `wiselyChoosenProgrammer.name + "(" + wiselyChoosenProgrammer.mainDomain + ")"`
    - `programmerIdx`
  - **Zauważ**, że występuje problem; prawdopodobnie lista programistów rośnie; zbadajmy ją poprzez:
    - zaznaczenie w edytorze jakiegokolwiek wystąpienia nazwy klasy `Progmrammer` -> *PPM* -> *InstancesCount*
    - podobnie: `Progmrammer` -> *PPM* -> *All Instances*
- Spróbuj znaleźć źródło problemu.
- Zakończ debugowanie.

### Dodatkowe kroki dot. *deatail formatter-a*:
- Podczas debugowanie; w widoku *Variables* znajdź gdzieś instancję klasy *Progmrammer*; jej podgląd to w tej chwili nieczytelny *toString*.
- *PPM* na wspomnianej instancji -> *New Detail Formatter*; w okienku napisz coś w rodzaju czytelnego *toStringa* dla wspomnianej klasy; wyświetl nazwę programisty oraz jego główną dziedzinę.
- Kontynuuj debugowanie zwracając uwagę na to, że w większości podglądów zmiennych instancje klasy `Programmer` są teraz wyświetlane w czytelny sposób - z użyciem wspomnianego *Detail Formattera*.
- Zakończ debugowanie.

-----

## [Przykład 4] : Modyfikacja zmiennych
Kod: `P4_Modyfikacja_Zmiennych_Lottery`

Tło przykładu:
> Kod przedstawia loterię losującą klienta; do jego rat zostanie zastosowana promocja; porównanie sum rat przed i po modufikacji wyświetlane jest pod koniec działania aplikacji.

> Wyobraź sobie, że *"biznes"* ma zastrzeżenia do działania aplikacji. Wg nich dla klienta nr **13** (pechowiec...) pod koniec działania mechanizmu zostały wyliczone niepoprawne raty.

Kolejne kroki ćwiczenia:
- Spróbuj uruchomić aplikację; próba zakończy się fiaskiem - nie masz dostępu do odpowiedniej grupy.
- Uruchom aplikację ale w trybie debugowania; przejdź do metody `makeDreamsComeTrue()`; wewnątrz jej:
  - przejdź do `checkIfOprInGroup()` - do linijki gdzie zmienna `oprCtx` jest już zainicjalizowana ale nie doszło do sprawdzenia grupy dostępowej
  - podmień `oprCtx` na nowy obiekt; znajdź zmienną w *Variables* -> *PPM* -> *Change Value*; w okienku wpisz kod, który stworzy nowy obiekt: `new OperatorCtx("Haker", java.util.Arrays.asList(1234));`; zatwierdź; wróć do metody `makeDreamsComeTrue()`
  - gdy wartość zmiennej `winnerId` zostanie już ustalona - jak najszybciej zmień jej wartość na `13`!
  - podobnie jak dla grupy dostępowej, gdy zostanie ustalony wynik sprawdzania ograniczeń dla klienta - czyli zmienna `results` - podmień jej wartość; tym razem w okienku wpisz kod przygotowujący i zmieniający obiekt do podmiany i zatwierdź:
     ```
      CheckingResult newResult = new CheckingResult();
      newResult.type = p4.restriction.CheckingResult.ResultType.NO_RESTRICTIONS;
      return newResult;
      ```
   - jak tylko lista rat klienta zostanie pobrana - zmienna `installmentsForUser` - zauważ w widoku *Variables*, że zawiera ona `null`-e; dostań się do nich i podmień je używając konstruktora `new Integer(0)`
   - w konstrukcji `for` pod koniec metody dodaj stosowne *expressions* i spróbuj dojść do tego w czym tkwi problem zgłaszany przez *"biznes"*
- Zakończ debugowanie.

-----

## [Przykład 5] : Uruchamianie kodu + force return
Kod: `P4_Modyfikacja_Zmiennych_Lottery`

Upewnij się, że:
- wykonałeś poprzednie ćwiczenie; teraz czas na przejście przez ten sam kod ale z użyciem innych *"bajerów"* ;)
- w perspektywie *Debug* masz dodany widok *Display*

Kolejne kroki ćwiczenia:
- Przejdź do wnętrza metody `makeDreamsComeTrue()` a anstępnie do wnętrza metody `checkIfOprInGroup()`.
  - po zaincjalizowaniu `oprCtx` a przed `if`-em - przetestuj, poprzez ewaluację w widoku *Debug*, czy jesteś w odpowiedniej grupie - przekopiuj warunek z `if`-a do *Display*; zaznacz kod i ewaluuj przyciskiem ![](img/display_eval.png)
  - dodaj odpowiednią grupę do kontekstu poprzez wykonanie kodu w *Display*; poniższy kod wklej, zaznacz i wykonaj przyciskiem ![](img/display_exec.png) (zwróć uwagę na testowe *"sysouty"*):
    ```
    System.out.println(oprCtx.getGroups());
    oprCtx.getGroups().add(1234);
    System.out.println(oprCtx.getGroups());
    ```
  - wróć do metody `makeDreamsComeTrue()`
- Wywołaj metodę *"z innej beczki*" - w *Display* ewaluuj kilkukrotnie np. `drawUserId()`.
- Wejdź do pierwszej linii metody `drawUserId()` w momencie inicjalizowania zmiennej `winnerId`.
  - wymuś natychmiastowe zwrócenie wartości `12` **!** poprzez wpisanie jej w *Display* zaznaczeniu, *PPM* -> *Force Return*
- Podobnie - wejdź do pierwszej linii `checkForUser(..)` i ponownie wymuś natychmiastowe zwrócenie wyniku; tym razem jest to obiekt; posiłkując się kodem z metody - w *Display* - przygotuj obiekt a na końcu zwróć go przez `return`; zaznacz kod, *PPM* -> *Force Return*; wspomniany kod:
    ```
    CheckingResult cr = new CheckingResult();
    cr.type = NO_RESTRICTIONS;
    return cr;
    ```
- Kontynnuj debugowanie aż do momentu przypisania listy rat do zmiennej `installmentsForUser`; zwróć uwagę, że lista zawiera dużo `null`-i
- W widoku *Display* wykonaj *testowy* kod modyfikujący - w ramach osobnej listy popraw kolecję rat:
  ```
  List<Integer> correctList = new java.util.ArrayList<>();
  for (Integer inst : installmentsForUser) {
    correctList.add( inst == null ? new Integer(0) : inst);
  };
  System.out.println(correctList);
  ```
- Jeżeli powyższe wykonanie wyświetla w konsoli poprawną listę, dodaj do kodu linię podmieniającą listy `installmentsForUser = correctList;` ; uruchom ponownie kod; sprawdź czy lista jest poprawiona.
- Zakończ debugowanie.

-----

## [Przykład 6] : 'Wyjątkowy' BP ;)
Kod: `P6_Exception_BP_MiniGUI`

Kolejne kroki ćwiczenia:
- Uruchom przykład; klikaj w przycisk w GUI; spróbuj przewidzieć kiedy występuje błąd... nie przewidzisz :)
- W widoku *Breakpoints* dodaj *Exception Breakpoint*; użyj przycisku ![](img/bp_exception.png)
  - w okienku znajdź i  wybierz *"szeroką"* klasę wyjątków tj. `java.lang.Exception`; zaznacz reagowanie na  wyjątki *caught* i *uncaught*; zatwierdź
  - dodatkowo, w widoku *Breakpoints*, w dodatkowych ustawieniach nowego BP zaznacz *Subcalsses of this exception*
- Uruchom kod w trybie debugowania:
  - znudź się przeklikiwaniem wystąpień kolejnych, nieistotnych dla Ciebie, wyjątków
  - przerwij debugowanie
- W widoku *Breakpoints* -> *PPM* na naszym *BP* -> *Breakpoint Properties* -> *Filtering*:
  - w okienku zawęź reagowanie na wyjątki tylko do pakietu `p6`
- Uruchom ponownie debugowanie.
- Klikaj przycisk aż trafisz na wyjątek; na podstawie stosu ramek w *Debug* znajdziesz miejsce wystąpienia wyjątku (Eclipse najprawdopodobniej przeniesie Cię też do odpowiedniej linii kodu).
- Zakończ debugowanie.

-----

## [Przykład 7] : Warunkowy BP
Kod: `P7_Warunkowy_BP_ProcessingLoop`

> Wyobraź sobie, że chcesz przeanalizować kod który jest wywoływany mnóstwo razy; Ciebie interesuje tylko jedno z wywołań - dla konkretnych argumentów/zmiennych...

Kolejne kroki ćwiczenia:
- Uruchom przykład; spróbuj pobieżnie przeanalizować kod.
- Dodaj BP w metodzie `main` , wewnątrz pętli - w linii `p.process(inst);`.
- Spróbuj, używając podglądu zmiennych i przycisku *Resume*, *"zapolować"* na wywołanie metody `process(..)` dla obiektu klasy `Installment` z polem `amount` równym `123` oraz polem `type` równym `InstallmentType.VARIABLE`.
- Stwierdź, że jest to mozolne i przerwij debugowanie.
- Znajdź dodany BP w widoku *Berakpoints* -> *PPM* -> Zaznacz checkbox *Conditional* i w polu poniżej wpisz warunek, który ewaluuje się do `true`/`false`:
  ```
  inst.amount == 123 && inst.type.equals(p7.repos.InstallmentType.VARIABLE)
  ```
- Zapisz zmiany (*Apply and Close*).
- Ponownie uruchom debugowanie; gdy debugger się zatrzyma - sprawdź czy zgadzają się warunki zatrzymania.
- Zakończ debugowanie.

-----

## [Przykład 8] : Połączenie zdalne do działającej JVM
Kod: `P8_Worker` oraz `P8_Helper`

> Ćwiczenie będzie składało się z dwóch faz. Pierwszą z nich będzie uruchomienie "z palca" przygotowanego kodu na JVM **nie**-zarządzanej przez Eclipse. Drugim krokiem będzie połączenie się z tą JVM z Eclipse-a. Kulminacją będzie próba debugowania zdalnej JVM z Eclipse-m jako klientem-debuggerem.

### Krok 1 : Uruchomienie kodu na osobnej JVM
- Zapisz sobie gdzieś źródła przykładów `P8_Worker` oraz `P8_Helper`; **nie** importuj ich do Eclipse!
- Oba projekty zainstaluj do swojego lokalnego repozytorium używając polecenia `mvn clean install` w linii komend; ważne jest to aby projekty trafiły do Twojego lokalnego repozytorium oraz aby ich *jar*-y trafiły do podkatalogów */target*.
- Do osobnego folderu skopiuj wspomniane *jar*-y z katalogów */target* obu projektów.
- Zmień ich nazwy na coś prostszego: *worker.jar* oraz *helper.jar*.
- W linii poleceń, będąc w katalogu z *jar*-ami wykonaj polecenie: `java -cp worker.jar:helper.jar p8.worker.Worker`.
- Powinno dojść do uruchomienia programu sumującego niewielkie losowe liczby całkowite.
- Zakończ działanie aplikacji.

### Krok 2 : Uruchomienie kodu na osobnej JVM przygotowanej do zdalnego debugowania
- Ponownie uruchom kod z przygotowanych *jar*-ów ale tym razem dodaj parametr, który sprawi, że JVM będzie debugowalna i będzie czekać na połączenia na wskazanym porcie (**36203**); słowem - uruchom:
  ```
  java -agentlib:jdwp=transport=dt_socket,suspend=n,server=y,address=36203 -cp worker.jar:helper.jar p8.worker.Worker
  ```
- Nie zamykaj linii poleceń; **niech aplikacja cały czas działa!**

### Krok 3 : Połączenie się do JVM z Eclipse
- Przygotuj pusty workspace w Eclipse; dodaj tam pusty projekt maven-owy.
- Przejdź do perspektywy *Debbug*.
- Rozwiń opcje przy przycisku *Debug As* -> *Debug Configurations* -> Dodaj nowy element w kategorii *Remote Java Application*; skonfiguruj go:
  - dodaj czytelną nazwę
  - wskaż przygotowany pusty projekt (w skrócie: będzie to miejsce które Eclipse przeszuka szukając klas do wyświetlania podczas debugowania)
  - upewnij się, że typ połączenia to *Socket Attach*
  - wpisz adres/nazwę hosta : *localhost*
  - wpisz nr portu, z którym uruchomiliśmy tryb debugg naszej JVM... czyli *36203*
- Klikając *Debug* połącz się z JVM.
- Połączenie powinno być widoczne w widoku *Debug*; przejrzyj je.
- :bulb: Zwróć uwagę, że nasz wątek sumujący nadal działa - nie został dodany żaden BP; dodanie takowego jest w tym przypadku niemożliwe - nasz projekt jest pusty i nie ma kodu, w który można by się *"wkliknąć"* i dodać BP...
- Znajdź w Eclipse, obok podstawowych przycisków nawigacji podczas debugowania, przycisk *Suspend* (taka pauza); **na razie go nie klikaj!**
- :bulb: Zrobimy coś nietypowego; zapauzujemy wybrany wątek; JVM zachowa się tak jakby trafiła na BP i przekaże sterowanie do Eclipse; w widoku *Debug* znajdź i zaznacz wątek o nazwie *"ToDoThread"* a następnie użyj przycisku *Suspend*.
 - Przejrzyj stos ramek; zwróć uwagę na to, że *"corowe"* klasy Javy - czyli `Thread` - Eclipse bez trudu wyświetla; problem jest z kodem z klasy `Worker`; rozwiąż ten problem na jeden z dwóch sposobów:
   - **Sposób I** : Rozłącz się z JVM (przycisk *Disconnect* gdzieś obok *Suspend*); we wspomnianym pustym projekcie dodaj w POM-ie zależność do projektu *P8_Worker* (`groupId: pl.abof.debugg`, `artifactId: P8_Worker`, `version: 0.0.1-SNAPSHOT`); upewnij się, że z projektem jest wszystko OK; powtórz połączenie do JVM oraz wstrzymanie JVM; Eclipse powinien dodać zależności wskazanego w definicji połączenia projektu do przeszukiwanego obszaru podczas debugowania; zrobione!
   - **Sposób II** : Powtórz ćwiczenie, aż do momentu przeglądania stosu ramek; przejdź do ramki z nieosiągalnym kodem klasy `Worker`; w oknie edytora kliknij *Edit Source Lookup Path* -> *Add* -> wybierz opcję wskazywania źródeł z folderu - *File System Directory* -> wskaż folder *src* projektu *P8_Worker*; poczekaj chwilę; zrobione!
- Zakończ debugowanie.

-----

## [Przykład 9] : Połączenie do Wildfly / JBoss
Kod: **brak**; sam sobie zorganizuj; łącznie z serwerrem ;)

> Spróbuj, poprzez analogię, wyobrazić sobie poprzednie ćwiczenie z JVM uruchomionego serwera aplikacji (Wildfly / JBoss).

> Przygotuj lokalną wersję jakiegoś serwera; znajdź *beana* z metodą serwerową (lub *facade* "pod nim"), którą można wywołać delegatem; metoda ta powinna być prostą metodą pobierającą dane z bazy danych; ważne jest również to, żeby można było dodać BP w klasie która tworzy `Query` w HQL-u oraz ma dostęp do `EntityManager`-a.

> **Przykład:** `LegalBailiffBean` i metoda `getAllBailiff()`; chodzi o to, że można ją wywołać delegatem; BP - co jest istotne - zostanie jednak dodany poziom *"niżej"* - w analogicznej metodzie w `BailiffFacadeBean` bo z tej fasady korzysta wspomniany *bean*; w ramach tej metody jest też dostęp do wstrzykniętego `EntityManager`-a.

> Ćwiczenie składa się z podobnych do poprzedniego przykładu kroków.

### Krok 1 : Uruchomienie lokalnego serwera przygotowanego do zdalnego debugowania
- Aby uruchomić serwer w trybie debug zmodyfikuj plik `bin/standalone.conf` - odkomentuj linię opisaną jako *Sample JPDA settings for remote socket debugging*; zapamiętaj **port** z odkomentowanej linii!
- Uruchom serwer.

### Krok 2 : Połączenie się do JVM z Eclipse
- Odtwórz ten krok z poprzedniego ćwiczenia; pamiętaj o wpisaniu zapamiętanego portu! Istotne jest również to, abyś w Eclipse miał zaimportowany kod modułu, który chcesz debugować; moduł ten podaj w parametrach *"debugowego"* połączenia

### Krok 3 : Dodanie BP i uruchomienie kodu na serwerze
- Dodaj BP na wejściu metody, którą chcesz debugować
- Uruchom kod w Eclipse (delegat?), który sprawi, że serwer wykona fragment kodu, który chcesz debugować; użycie delegata nie może być uruchomione *normalnie* nie musisz klikać w *robaczka* ;)
- Powinno dojść do wykonania na serwerze linii z BP; serwer powinien zatrzymać JVM i przekazać sterowanie do Eclipse, który, dysponując kodem źródłowym, powinien wyświetlić linijkę w której JVM się zatrzymała

### Przykładowe wprawki podczas debugowania
> Poniżej znajdziesz przykłady ćwiczeń; istotny jest dostęp do `EntityManagera`; warto też wyświetlać po stronie klienta wyniki wywołań delegata!

#### > Force return
- Zamiast wykonywać zapytanie spreparuj listę z pojedynczą encją i użyj *force return*

#### > Podmień obiekt zapytania
- Po stworzeniu obiektu `Query`, a przed jego *"wykonaniem"* stwórz własny obiekt `Query` i zamień je dzięki widokowi *Display*; np:
  ```
  Query q = em.createQuery("HQLowe zapytanie z dodanym np warunkiem na id...");
  queryOryginalne = q;
  ```

#### > Wykonaj testowe zapytanie
- Skorzystaj z dostępu do `EntityManager`-a i wykonaj *"obok"*, podczas pauzy wątku, jakieś zapytanie; jego wynik wyświetl w logach serwerowych; np:
  ```
  Query q = em.createQuery("SELECT p FROM Person p WHERE p.id > 200 and p.firstName LIKE U% ORDER BY p.firstName");
  List<Person> res = q.getResultList();
  for (Person p : res) {
    System.out.println(p.getFirstName() + " " + p.getSurname());
  }
  ```

#### > Wykonaj inną metodę z beana / fasady
- Pamiętaj, że jesteś *"wewnątrz"* obiektu który ma inne metody; spróbuj wywołać jedną z pozostałych metod!

#### > Debuguj kod serwerowy
- Spróbuj, używając *Step into* wejść w głąb wywołania prostego zapytania; włącz *step filter* i spróbuj dodatkowo odfiltrować pakiety `org.jboss`, `com.arjuna`, `org.hibernate`; sprawdź na http://grepcode.com/ do jakiej klasy trafiłeś; jeżeli jest to *sterownik* postgres-owy spróbuj określić jego wersję (najprawdopodobniej `9.1-903.jdbc4`); ściągnij źródła sterownika z https://jdbc.postgresql.org/download.html rozpakuj je i wskaż je Eclipse-owi aby pozwolił ci podziwiać debugowany kod :)
