Магазин игрушек (Java)
Информация о проекте
Необходимо написать проект, для розыгрыша в магазине игрушек. Функционал должен содержать добавление новых игрушек и задания веса для выпадения игрушек.

Как сдавать проект
Для сдачи проекта необходимо создать отдельный общедоступный репозиторий(Github, gitlub, или Bitbucket). Разработку вести в этом репозитории, использовать пул реквесты на изменения. Программа должна запускаться и работать, ошибок при выполнении программы быть не должно. 
Программа, может использоваться в различных системах, поэтому необходимо разработать класс в виде конструктора

Напишите класс-конструктор у которого принимает минимум 3 строки, содержащие три поля id игрушки, текстовое название и частоту выпадения игрушки

Из принятой строки id и частоты выпадения(веса) заполнить минимум три массива.

Используя API коллекцию: java.util.PriorityQueue добавить элементы в коллекцию

Организовать общую очередь

Вызвать Get 10 раз и записать результат в файл

Не совсем четко прописано задание, сделал его так, как понял.

Разбил программу на 2 файла:
* Toy - содержит класс Toy
* ToyLottery - основное тело программы. Переписывал в черновике раза 3. Сначала сделал вариант, где проводится именно лотерея, при помощи рандомного числа (реализацию добавил в отдельный фаил variantOfLottery, но она не будет работать с итоговой версией программы.
              Реализация давала реальный рандом и эффект лотереи. Затем понял, что это не верно, и переписал. Теперь элемент просто берется из очереди.
