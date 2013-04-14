(ns nightweb-desktop.pages
  (:use [hiccup.core :only [html]]
        [nightweb-desktop.views :only [get-action-bar-view
                                       get-tab-view
                                       get-user-view
                                       get-post-view
                                       get-category-view]]))

(defmacro get-page
  [& body]
  `(html [:head
          [:title "Nightweb"]
          [:link {:rel "stylesheet" :href "foundation.min.css"}]
          [:link {:rel "stylesheet" :href "foundation.override.css"}]
          [:link {:rel "stylesheet" :href "fonts/general_foundicons.css"}]]
         [:body
          ~@body
          [:script {:src "zepto.js"}]
          [:script {:src "foundation.min.js"}]
          [:script "$(document).foundation();"]]))

(defn get-main-page
  [params]
  (get-page
    (get-action-bar-view (get-tab-view params true))))

(defn get-category-page
  [params]
  (get-page
    (get-action-bar-view (get-tab-view params false))))

(defn get-basic-page
  [params]
  (get-page
    (get-action-bar-view nil)
    (case (get params :type)
      :user (if (get params :userhash)
              (get-user-view params)
              (get-category-view params))
      :post (if (get params :time)
              (get-post-view params)
              (get-category-view params))
      :tag (get-category-view params)
      [:h2 "There doesn't seem to be anything here."])))