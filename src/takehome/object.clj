(ns takehome.object)

(defn is-series? [object]
      (= (:type object) :series))

(defn patriota-can-access? [object]
      (case (:type object)
            (:podcast :debate :interview) true
            false))

(defn premium-can-access? [object]
      (case (:type object)
            (:podcast :debate :interview :course) true
            false))