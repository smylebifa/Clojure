; Присвоение f функции, суммирующей 2 параметра...
(def f (fn [a b] (+ a b)))

; Компактная форма объявление функции f...
(defn f [a b] (+ a b))

; Локальное объявление переменных и выполнение действий с ними...
(let [a 5, b 6] (+ a b))


; Положить в начало списка элемент...
(conj (list 1 2 3 4) 5)

; Удалить элемент в начале списка...
(pop '(1 2 3 4 5))

; Берет из списка первый элемент...
(peek (list 1 2 3 4 5))


; Добавление элемента в конец вектора...
(conj [1 2 3] 4)

; Получение элемента по индексу...
(get [1 2 3 4] 2)

; Объявление переменной вектора и обращение по индексу в компактном виде...
(def a [1 2 3 4])
(a 3)

; Изменить значение элемента по индексу...
(assoc [1 2 3 4 5] 2 6)


; Получение по ключу элемента из ассоциативного массива(хеша)...
({:a 1, :b 2} :a)

; Получение вложенного элемента хеша...
(get-in {
         :a 1, 
         :inn {
               :b [4 5 6]
              }
        } [ :inn :b ])


; Создание множества(уникальные идентификаторы)...
(def a #{1 2 3})


; Функция преобразования типов(создание вектора из списка)...
(into [] (list 1 2 3))


; Создание последовательности из одного элемента
; (задается как первый элемент и все остальные)...
(cons 1 nil)

; Создание переменной последовательности из 3 элементов...
(def a (cons 1 (cons 2 (cons 3 nil))))

; Получение первого элемента...
(first a)

; Получение последних элементов в виде последовательности...
(rest a)

; Получение первых 2 элементов...
(take 2 [1 2 3 4])

; Удалить 2 первых элемента...
(drop 2 [1 2 3 4])

; Взять 3 элемента из последовательности , 
; состоящей из числа 2 и сгенерированных 5...
(take 3 (cons 2 (repeat 5)))


; Проверка условия 4>5 и вывод ответа,
; Yes, если условие выполняется, No в обратном случае...
(if (> 4 5) "Yes" "No")

; Условие множественное, если не выполнилось первое, проверяем второе...
(cond (> 4 5) "4>5"
      (< 5 6) "5<6")


; Создание рекурсии, первый оператор задает счетчик,
; затем проверяется значение счетчика и выполняется действие,
; второй оператор вызывает рекурентно эту же функцию, 
; с инкрементированным значением счетчика.
; Первый вариант реализации...
(let [v [1 2 3 4]
      size (count v)]
    (loop [i 0]
      (if (< i size)
        (do (println i (v i))
            (recur (inc i))))))

; Второй вариант(when == if без else, не нужен do)...
(let [v [1 2 3 4]
      size (count v)]
  (loop [i 0]
    (when (< i size)
      (println i (v i))
      (recur (inc i)))))


; Вывод в цикле значений...
(doseq [x [1 2 3]]
  (println x))


; Создание последовательности начиная с start элемента и заканчивая end элементом...
(defn nums [start end] 
  (if (>= start end)
    nil
    (cons start (nums (inc start) end)))
  ) (nums 1 15)

; Создание последовательности и взятие 10 элементов из нее...
(defn nums [start]
  (cons start (lazy-seq (nums (inc start))))) 
(take 10 (nums 1))


; Функция вычисляет рекурентно новую последовательность 
; на основе исходной последовательности и удаленного из нее значения...
(defn removex [val l]
  (when-not (empty? l)
    (if (= val (first l))
      (recur val (rest l))
      (cons (first l) (lazy-seq (removex val (rest l))))))) 

(removex 3 [1 2 3 3 4 4 3 4 3 4])


; /////////////////////////////////////////////////////////////////////////

; Макрос nth принимает список и индекс, возвращает значение по индексу...
(nth coll index)

; /////////////////////////////////////////////////////////////////////////

; Макрос map возвращает ленивую последовательность,
; которая равна результату применения функции к списку...
(map f)(map f coll)(map f c1 c2)(map f c1 c2 c3)(map f c1 c2 c3 & colls)

(map inc [1 2 3 4 5])
;;=> (2 3 4 5 6)

(map + [1 2 3] [4 5 6])
;;=> (5 7 9)

(map #(str "Hello " % "!" ) ["Ford" "Arthur" "Tricia"])
;;=> ("Hello Ford!" "Hello Arthur!" "Hello Tricia!")

; //////////////////////////////////////////////////////////////////////////


; Макрос partition, который возвращает ленивую последовательность из списков,
; с определенным количеством в каждом   
(partition n coll)(partition n step coll)(partition n step pad coll)

(partition 4 (range 20))
;;=> ((0 1 2 3) (4 5 6 7) (8 9 10 11) (12 13 14 15) (16 17 18 19))

; /////////////////////////////////////////////////////////////////////////

; Макрос complement принимает функцию и возвращает обратный ей эффект...
(complement f)

(def not-empty? (complement empty?))

(not-empty? [])    ;;=> false
(not-empty? [1 2]) ;;=> true

(map (complement even?) '(1 2 3 4))
;; (true false true false)

; //////////////////////////////////////////////////////////////////////////

; Получаем список из вектора с применением фильтра в виде анонимной функции.
; 1 вариант...
(filter (fn [a] (> a 3)) [-2 -4 1 2 5 9 1 7])
=> (5 9 7)

; 2 вариант...
(filter #(> % 3) [-2 -4 1 2 5 9 1 7])
=> (5 9 7)

; Если параметров несколько, можно их перечислять цифрами начиная с 1...
(filter #(> %1 3) [-2 -4 1 2 5 9 1 7])
=> (5 9 7)


; Функция map, применяет функцию inc ко всем элементам вектора...
(map inc [1 2 3 4 5])
=> (2 3 4 5 6)

; Функция reduce применяет операцию сложения к каждому элементу и получает сумму...
(reduce + [1 2 3 4 5])
=> 15

; Функция reduce добавляет элементы из вектора в другой пустой вектор(в конец)...
(reduce conj [] [1 2 3 4 5])
=> [1 2 3 4 5]

; Функция reduce добавляет элементы из вектора в список(в начало)...
(reduce conj () [1 2 3 4 5])
=> (5 4 3 2 1)

; Функция map выводит списки элементов от 0 до i, получая i из каждого элемента вектора...
(map (fn [i] (range 0 i)) [1 2 3 4 5])
((0) (0 1) (0 1 2) (0 1 2 3) (0 1 2 3 4))

; Функция mapcat применяет функцию map и затем склеивает результат в один список...
(mapcat (fn [i] (range 0 i)) [1 2 3 4 5])
=> (0 0 1 0 1 2 0 1 2 3 0 1 2 3 4)

; Получение из списка строк их количества, сравнение больше ли оно 2,
; если да, то складываем количество каждой такой строки и выводим эту сумму.
; 1 вариант...
(reduce + (filter #(> % 2) (map count ["asdfgasd" "as" "asdasf" "a" "sdfv"]))) 
=> 18

; 2 вариант...
(->> ["asdfgasd" "as" "asdasf" "a" "sdfv"] (map count) (filter #(> % 2)) (reduce +))
=> 18

; Пример с использованием for чтобы вывести с списке количество символов переданного вектора строк...
(for [str ["asdfgasd" "as" "asdasf" "a" "sdfv"] 
      :let [l (count str)]
      :when (> l 2)] 
  l)
=> (8 6 4)


; Применение объдинения элементов из векторов каждый с каждым...
(for [x [1 2 3] y [4 5 6]] [x y])
=> ([1 4] [1 5] [1 6] [2 4] [2 5] [2 6] [3 4] [3 5] [3 6])


; Функция distinct позволяет получать различающиеся строки из всех выбранных...
(distinct ["a" "a" "asda" "df" "asd" "df"])
=> ("a" "asda" "df" "asd")


; Создание функции с добавлением документации, предопределенными параметрами,
; проверяющими являются ли они числами(предысуловием) и постусловием, 
; проверяющим результат выполнения функции, является ли он числом...
(defn sum "Return sum of parameters" 
  [a b] 
  {:pre [(number? a) (number? b)]
   :post [(number? %)]} (+ a b))
=> #'user/sum

; Просмотр документации по функции sum...
(doc sum)
-------------------------
user/sum
([a b])
  Return sum of parameters
=> nil


; Добавляем тип для параметра, для вывода ошибка в случае переданного не соответствующего ему типа, 
; также вызываем функцию из Java и получаем индекс входной подстроки...
(defn func [^String str] (.indexOf str "abc")) (func "bdnabc")
=> #'user/func
=> 3


; Создание функции, которая может принимать разное количество параметров, в данном случае 2 или 3...
(defn sum 
  ([a b] (+ a b))
  ([a b c] (+ a b c)))
=> #'user/sum
(sum 1 4) (sum 1 4 5)
=> 5
=> 10



; Принимает любое количество параметров в форме списка и вычисляет их сумму.
; 1 вариант...
(defn sum
  ([a b] (+ a b))
  ([a b c] ( + a b c))
  ([a b c & rest] (reduce + (+ a b c) rest)))
=> #'user/sum

(sum 1 3 4 5 6 7)
=> 26


; 2 вариант...
(defn sum
  ([a b] (+ a b))
  ([a b c] ( + a b c))
  ([a b c & rest] (apply + a b c rest)))
=> #'user/sum
(sum 1 3 4 5 6 7)
=> 26

; 3 вариант...
(defn sum
  [& elements] (apply + elements))
=> #'user/sum
(sum 1 3 4 5 6 7)
=> 26



; Деструктурирование вектора, _ служит для пропуска параметра...
(def v [1 2 3 4 5])
=> #'user/v

(let [[a _ b] v]
  (println "a =" a "b =" b))
a = 1 b = 3
=> nil

; Вложенное деструктурирование...
(let [[n1 n2 n3 [str1 str2]] [1 2 3 ["a" "b"]]]
  (println str1 str2 n2))
a b 2
=> nil


; Вывод деструктурированных параметров, в том числе всего списка в виде переменной full...
(let [[n1 n2 n3 [str1 str2] :as full] [1 2 3 ["a" "b"]]]
  (println str1 str2 n2 full))
a b 2 [1 2 3 [a b]]
=> nil


; Работа с map, получение значения по ключу...
(def john {:name "John" :surname "Smith"})
=> #'user/john

(let [p john] (:name p))
=> "John"


; Деструктурирование ассоц. массива и вывод в виде вектора.
; 1 вариант...
(let [{name :name surname :surname} john] [name surname])
=> ["John" "Smith"]

; 2 вариант для ключей...
(let [{:keys [name surname]} john] [name surname])
=> ["John" "Smith"]

; 2 вариант для строк...
(let [{:strs [name surname]} {"name" "John" "surname" "Smith"}] [name surname])
=> ["John" "Smith"]


; Задание значения age по умолчанию равным 20...
(let [{:strs [name surname age] :or {age 20}} {"name" "John" "surname" "Smith"} ] [name surname age])
=> ["John" "Smith" 20]


; Деструктурирование вложенного ассоциативного массива ...
(def john {:name "John" :surname "Smith" :address {:city "Moscow" :street "Vadkovsky per"}})
=> #'user/john

(let [{:keys [name surname] {:keys [city street]} :address} john] [name surname city street])
=> ["John" "Smith" "Moscow" "Vadkovsky per"]


; Создание макроса, понимающего обчную, не польскую нотацию...
(defmacro infix [a oper b] (list oper a b)) (infix 1 + 2)
=> #'user/infix
=> 3


; Написание макроса с обратным условием...
(defmacro unless [expression then else]
  (list 'if test else then)) (unless (> 5 2) (println "a < b") (println "a > b"))
=> #'user/unless
a > b



; Представление выражения в виде кода...
(defmacro unless [expression then else]
  `(if test else then)) 
'(unless (> 5 2) (println "a < b") (println "a > b"))
=> #'user/unless
=> (unless (> 5 2) (println "a < b") (println "a > b"))


; ~ Выступает в качестве параметра...
(defmacro unless [expression then else]
  `(if ~test ~else ~then)) 
(unless (> 5 2) (println "a < b") (println "a > b"))
=> #'user/unless
a > b
=> nil



; Разыменование ссылки(применяется для вызова promise, future).
; 1 вариант...
(deref reference)

; 2 вариант, более компактный...
(@reference)



; Пример с выполнением future, выбрасывающем исключение...
(def f (future (throw (Exception. "Hello from the future!")))) 
(@f)

Execution error at user/fn (form-init14986200828704233027.clj:1).
Hello from the future!



; Применение функции swap для изменения атома...
(defn example []
  (def myatom (atom 1))
  (println @myatom)

  (swap! myatom inc)
  (println @myatom)) (example)

1
2
=> #'user/example
=> nil



; Пример использования агента для вычисления суммирующего значения всех элементов вектора...
(def sum (agent 0)) 
(def numbers [0 9 3 4 5 5 4 44 4 2 5 6 7 775])

(doseq [x numbers]
  (send sum + x))

; Ожидание пока не выполнятся действия с sum...
(await sum)

(println @sum)

=> #'user/sum
=> #'user/numbers
=> nil
=> nil
873
=> nil



; Использование функции cycle для создания ленивых бесконечных цикличных последовательностей...
(take 5 (cycle ["a" "b"]))
=>("a" "b" "a" "b" "a")

user=> (take 10 (cycle (range 0 3)))
=>(0 1 2 0 1 2 0 1 2 0)


; Multiplies every x by every y...
(doseq [x [-1 0 1]
        y [1  2 3]]
  (prn (* x y)))
-1
-2
-3
0
0
0
1
2
3


; Применение функции map для реализации работы...
(map list [1 2 3] [1 2 3])
=> ((1 1) (2 2) (3 3))



; Использование функции send для агента...
user=> (def my-agent (agent 100))
#'user/my-agent

user=> @my-agent
100

user=> (send my-agent + 100)
#<Agent@5afc0f5: 200>

user=> @my-agent
200


; Пример использования метода wait для ожидания завершения потока или агента.
; Макрос send отправляет действие агенту...
(def agnt (agent {}))

(send-off agnt (fn [state] 
               (Thread/sleep 10000)
               (assoc state :done true)))
=> <Agent@5db18235: {}>

(await agnt)
=> nil



; Примеры использования макроса assoc
(assoc {} :key1 "value" :key2 "another value")
;;=> {:key2 "another value", :key1 "value"}

;; Here we see an overwrite by a second entry with the same key
(assoc {:key1 "old value1" :key2 "value2"} 
        :key1 "value1" :key3 "value3")
;;=> {:key3 "value3", :key2 "value2", :key1 "value1"}

;; 'assoc' can be used on a vector (but not a list), in this way: 
;; (assoc vec index replacement)
(assoc [1 2 3] 0 10)     ;;=> [10 2 3]
(assoc [1 2 3] 2 '(4 6)) ;;=> [1 2 (4 6)]



; Пример использования макроса spit для записи в файл, slurp для чтения...
user=> (spit "flubber.txt" "test")
nil
user=> (slurp "flubber.txt")
"test"


; Создание переменной типа volatile...
(def keep-running? (volatile! true))



; Пример использоваемя макроса apply...

(apply str ["str1" "str2" "str3"])  ;;=> "str1str2str3"
(str "str1" "str2" "str3")          ;;=> "str1str2str3"

(apply max [1 2 3])
;;=> 3 

;; which is the same as 
(max 1 2 3)
;;=> 3



; Пример использования блокировки...
(def log-lock (Object.))

(defn log [& args]
  (locking log-lock
    (apply println args)))

;; Thread 1
(log "INFO 2017-4-29: Starting database connection.")

;; Thread 2
(log "WARNING 2017-4-29: Cannot find configuration file, using defaults.")

INFO 2017-4-29: Starting database connection.
=> nil

WARNING 2017-4-29: Cannot find configuration file, using defaults.
=> nil



; Подключение библиотеки...
(require '[clojure.core.async :as async])



; Запуск в новом потоке вывода значений...
(def thread (Thread. (fn [] (println 1 2 3))))

(.start thread)



; Создание пула потоков и запуск на выполнение в новом потоке вывода на печать чисел...
(import 'java.util.concurrent.ExecutorService)
(import 'java.util.concurrent.Executors)

(def service (Executors/newFixedThreadPool 4))

(def f (.submit ^ExecutorService service
                ^Callable (fn []
                            (println 1 2 3))))

(println @f)


; Взятие 10 элементов из генератора случайных чисел...
(take 10 (repeatedly #(rand-int 100)))
=> (82 8 75 63 85 76 95 50 51 70)



; Макрос io ! позволяет помечать код, который не должен выполняться внутри транзакции...
(defn log [s]
   (io!
      (println s)))

(log "Hello World") ; succeeds

(dosync (log "Hello World!")) ; throws IllegalStateException



; Выполнение действий с атомом...
(defn counter []
  (let [cnt (atom 0)]
    {:inc! (fn [] (swap! cnt inc))
     :dec! (fn [] (swap! cnt dec)) 
     :get (fn [] @cnt)} ))

(let [cnt (counter)]
  ((:inc! cnt))
  ((:inc! cnt)) 
  ((:get cnt)))
=> 2



; Пример работы макроса rand-nth...
(def food [:ice-cream :steak :apple])
#'user/food

(rand-nth food)
:apple

(rand-nth food)
:ice-cream



; Пример использования макроса ->...
(conj (conj (conj [] 1) 2) 3)
[1 2 3]

; Тоже самое с использованием thread-first(->)...
(-> []
          (conj 1)
          (conj 2)
          (conj 3))
[1 2 3]



; Пример использования макроса thread-last(->>) для применения функции 
; в первом аргументе к коллекции в последнем.
; #() - создание анонимной функции...
user> (->> ["Japan" "China" "Korea"]
           (map clojure.string/upper-case)
           (map #(str "Hello " %)))
("Hello JAPAN!" "Hello CHINA!" "Hello KOREA!")



; Использование функций из Java...
(Math/pow 2 3)
8.0

(let [current_date (new java.util.Date)]
        (.toString current_date))
"Sun Jan 15 21:44:06 JST 2017"



; Include async lib...
(:use '[clojure.core.async :as async])


; Define ref...
(def my-ref (ref 0))


; Get value of ref...
(deref my-ref)


; Set value of ref...
(dosync (ref-set my-ref 1) (ref-set my-ref 4))


; Update value by incrementing old value using alter and function,
; that get current ref and change it.
; 1 variant.
(dosync (alter my-ref (fn [current-ref] (inc current-ref))))

; 2 variant...
(dosync (alter my-ref #(inc %)))