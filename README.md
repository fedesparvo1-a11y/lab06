# How to use this repository

entra in fedes

Recommended strategy:
* Create your own copy of this repository on your profile
      fork da github
* Clone this repository locally
      git clone git@github.com:unibo-oop/lab06.git
* Remove the `origin` remote (con git remote -v controlli i remote che ci sono)
      git remote remove origin
* Create a new `neworigin` remote pointing to your copy
      git remote add neworigin git@github.com:fedesparvo1-a11y/lab06.git
* Push the exercises branch on your copy, setting the branch upstream contextually (use `git push -u origin exercises`)
      git push -u neworigin exercises
* Commit progressively when resolving the exercise and push changes on your repo
