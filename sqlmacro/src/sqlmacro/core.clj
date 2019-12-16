(def <> not=)

(def entries '({:month 1 :val 12 :s1 true :s2 false}
               {:month 2 :val 3 :s1 false :s2 true}
               {:month 3 :val 32 :s1 true :s2 false}
               {:month 4 :val 18 :s1 true :s2 false}
               {:month 5 :val 32 :s1 false :s2 true}
               {:month 6 :val 62 :s1 false :s2 true}
               {:month 7 :val 12 :s1 false :s2 true}
               {:month 8 :val 142 :s1 true :s2 false}
               {:month 9 :val 52 :s1 true :s2 false}
               {:month 10 :val 18 :s1 true :s2 false}
               {:month 11 :val 23 :s1 false :s2 true}
               {:month 12 :val 56 :s1 false :s2 true}))

(def entries2 '({:month 1 :val 12 :s1 true :s2 false}))

(def persons '({:id 1 :name "olle"} 
               {:id 2 :name "anna"} 
               {:id 3 :name "isak"} 
               {:id 4 :name "beatrice"}))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;




(defmacro select [[& wantedColumns] _ table _ [condColumn condOp condValue] _ orderArg]
  
  (defn filterCond [table condOp condColumn condValue]
    (filter #(condOp (condColumn %) condValue) table))

  (defn getColumns [wantedColumns table returnValue] 
    (if (empty? (rest table))
    ;; seq nedan för att konvertera returvärdet från vektor [] till lista ()
      (seq (conj returnValue (select-keys (first table) wantedColumns)))
      (getColumns wantedColumns (rest table) (conj returnValue (select-keys (first table) wantedColumns)))))
;; recur?
  (defn orderTable [table orderArg]
    (sort-by orderArg table))
  
  `(getColumns [~@wantedColumns]
  ;; wantedColumns sparas i en lista (pga &) men när den används i select-keys funktionen
  ;; i getColumns så måste de ligga i en vektor. Därav tar vi bort paranteserna och lägger
  ;; värdena i en vektor istället
    (filterCond (sort-by ~orderArg ~table) ~condOp ~condColumn ~condValue) []))

;; Ang. [] i slutet på raden ovan.
;; Vi skickar [] som returnValue till getColumns för att när man använder conj på en vektor
;; så läggs värdet till i slutet av vektoren. Om vi hade skickat en list () istället
;; så läggs varje värde till i början, vilket förstör ordningen

;; Ändrade ordningen på hur saker utförs i select macrot (nu sorteras de först, sedan filtreras
;; och sist plockas de relevanta kolumnerna ut (typ motsat mot vad vi hade förut)).
;; Detta för att göra det möjligt att orderby och/eller filtrera på kolumn värden som
;; inte är med i select (t.ex. sortera efter id men bara visa namn). Som vi hade förut så
;; började vi med att "slänga" bort id kolumnen vilket gjorde detta omöjligt.



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



(select [:id :name] from persons where [:id < 9] orderby :id) 

(select [:month] from entries where [:s1 = true] orderby :val)

(getColumns [:s2 :month] entries ())

(getColumns [:s2] () ())


;; så pass simpel att enklare att sortera direkt i macrot?






