
# Wyjaśnienia pojęć w Androidzie (Kotlin)

### 1. `by lazy`
`by lazy` to mechanizm w Kotlinie, który umożliwia opóźnioną inicjalizację zmiennej. Oznacza to, że zmienna zostanie zainicjowana dopiero w momencie, gdy będzie naprawdę potrzebna, a nie przy jej deklaracji. To pozwala zaoszczędzić zasoby, jeśli zmienna nie jest używana, ponieważ jej inicjalizacja jest opóźniona do momentu, kiedy zostanie do niej po raz pierwszy odwołanie.

### 2. RecyclerView
`RecyclerView` to widok w Androidzie, który służy do wyświetlania dużych zbiorów danych w sposób efektywny. Dzięki niemu aplikacja może wyświetlać listy danych, które są w stanie dynamicznie się zmieniać. Kluczowym elementem `RecyclerView` jest mechanizm ponownego używania widoków, co pozwala oszczędzać pamięć i poprawiać wydajność aplikacji, zwłaszcza w przypadku dużych list.

### 3. Co to jest aktywność?
Aktywność (ang. `Activity`) w Androidzie to komponent aplikacji, który reprezentuje pojedynczy ekran, na którym użytkownik wchodzi w interakcję z aplikacją. Każda aktywność jest odpowiedzialna za zarządzanie interfejsem użytkownika oraz procesem komunikacji z innymi komponentami aplikacji. Aktywność ma swój cykl życia, który umożliwia zarządzanie zasobami, pamięcią i interakcjami użytkownika.

### 4. Stany życia aplikacji

Android ma określony cykl życia aplikacji, który obejmuje różne etapy aktywności. Każdy etap to określona faza, w której aktywność może reagować na różne zdarzenia, takie jak rozpoczęcie, pauza, zatrzymanie lub zniszczenie. Dzięki tym metodom system zarządza pamięcią i zasobami aplikacji w odpowiednich momentach, co zapewnia stabilność aplikacji.

#### Metody cyklu życia aktywności:

- **onCreate()**: Tworzenie aktywności. Jest wywoływana tylko raz przy tworzeniu aktywności.
- **onStart()**: Aktywność staje się widoczna, ale nie jest jeszcze interaktywna.
- **onResume()**: Aktywność staje się aktywna i interaktywna. Użytkownik może teraz z nią wchodzić w interakcje.
- **onPause()**: Aktywność traci fokus, ale jest nadal widoczna. Może służyć do zapisania danych.
- **onStop()**: Aktywność staje się niewidoczna.
- **onRestart()**: Aktywność wraca do widoku po zatrzymaniu.
- **onDestroy()**: Aktywność jest niszczona, system może zwolnić jej zasoby.

### 5. `Context`
`Context` w Androidzie to interfejs, który daje dostęp do zasobów aplikacji oraz systemowych usług. Umożliwia on dostęp do elementów takich jak system plików, usługi sieciowe, a także pozwala na uruchamianie nowych aktywności lub fragmentów. Każda aktywność oraz fragment mają dostęp do `Context`, co pozwala im na interakcję z innymi komponentami aplikacji.

### 6. Intent
`Intent` to obiekt w Androidzie, który umożliwia komunikację pomiędzy komponentami aplikacji, takimi jak aktywności, serwisy czy odbiorcy zdarzeń. Za pomocą `Intent` można uruchamiać nowe aktywności, przesyłać dane między komponentami, lub wykonywać inne operacje, takie jak otwieranie strony internetowej.

### 7. Cykl życia fragmentów
Fragmenty mają własny cykl życia, który jest podobny do cyklu życia aktywności, ale bardziej elastyczny. Fragmenty mogą być wielokrotnie wykorzystywane w różnych aktywnościach, a ich cykl życia pozwala na odpowiednie zarządzanie zasobami i interakcją z użytkownikiem w różnych sytuacjach, np. w przypadku zmiany konfiguracji urządzenia.

### 8. Bundle
`Bundle` to obiekt w Androidzie, który pozwala na przechowywanie par klucz-wartość. Jest używany głównie do przesyłania danych między komponentami aplikacji, na przykład między aktywnościami lub fragmentami. Może przechowywać różne typy danych, takie jak liczby, teksty, tablice, a także obiekty, które implementują interfejs `Parcelable`.

### 9. Companion Object
`Companion object` to specjalny obiekt w Kotlinie, który pozwala na umieszczanie w klasie elementów statycznych. Kotlin nie posiada tradycyjnych elementów statycznych, jak Java, ale `companion object` jest sposobem na osiągnięcie podobnego efektu. Wszystkie elementy w `companion object` są dostępne bez konieczności tworzenia instancji klasy.

### 10. Intent – Domniemane i jawne
- **Jawny (`Explicit Intent`)**: Określa dokładnie, który komponent (np. aktywność lub serwis) ma zostać uruchomiony. Używa się go, gdy chcemy wskazać konkretny cel, np. przejść do określonej aktywności.
- **Domniemany (`Implicit Intent`)**: Określa, co ma zostać wykonane, ale nie wskazuje konkretnego komponentu. System wybiera odpowiednią aplikację lub komponent do obsługi danego zadania, np. otwarcie strony internetowej.

