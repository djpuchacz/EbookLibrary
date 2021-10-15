# EbookLibrary
Testy automatyczne dla backendu i frontendu tworzonej właśnie prostej aplikacji wypożyczalni eBooków.
Aplikacja jest dostępna tutaj: https://ta-ebookrental-fe.herokuapp.com
Tworzona aplikacja ma za zadanie realizować następującą funkcjonalność:

Ekran nr 1 – logowanie/rejestracja nowego użytkownika

Pozostałe ekrany dostępne są po zalogowaniu.

Ekran nr 2 – lista tytułów.

wyświetlona lista: autor, tytuł, rok wydania
możliwość dodawania, edycji, usuwania, wejścia w listę egzemplarzy
Ekran nr 3 – lista egzemplarzy

wyświetlona lista: data zakupu, status (na stanie, wypożyczona)
możliwość dodawania egzemplarzy, edycji, usuwania, wejścia w listę wypożyczeń
Ekran nr 4 – lista wypożyczeń

wyświetlona lista: imię i nazwisko klienta, data wypożyczenia, data wygaśnięcia
możliwość wprowadzenia i usunięcia wypożyczenia
możliwość edycji
Ekran nr 5 – wypożyczenie

formatka z polami: imię i nazwisko klienta (1 pole), data wypożyczenia, data wygaśnięcia
przy wejściu jako nowe wypożyczenie dostępne do edycji dwa pierwsze pola (drugie ustawia się domyślnie na sysdate()+3, pierwsze na sysdate())
przy wejściu jako edycja dostępne do edycji wszystkie pola.
Każdy użytkownik pracuje niezależnie na swojej liście eBooków – jest to realizowane przez backend: https://github.com/kodilla/ta-ebooklibrary-backend
