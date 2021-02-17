(ns takehome.core
    (:require [java-time :as time]
      [takehome.object :as o]
      [takehome.member :as m]))

; if (object.type == series)
; if (purchase.type == patriota && object.type in (podcast, debate, interview) && purchase is active)
; if (purchase.type == premium && object.type in (podcast, debate, interview, course) && purchase is active)
; if (purchase.type == mecenas && object.type in (podcast, debate, interview, course, patron) && purchase is active)
(defn can-access? [object purchase]
      (cond
        (o/is-series? object) true
        (and (m/is-patriota? object purchase) (m/is-active? object purchase)) true
        (and (m/is-premium? object purchase) (m/is-active? object purchase)) true
        (and (m/is-mecenas? object purchase) (m/is-active? object purchase)) true
        :else false))
