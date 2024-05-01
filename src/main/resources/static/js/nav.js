 // add hovered class to selected list item
 let list = document.querySelectorAll(".navigation li");

 function activeLink() {
   list.forEach((item) => {
     item.classList.remove("hovered");
   });
   this.classList.add("hovered");
 }

 list.forEach((item) => item.addEventListener("mouseover", activeLink));

 // Menu Toggle
 let toggle = document.querySelector(".toggle");
 let navigation = document.querySelector(".navigation");
 let main = document.querySelector(".main");

 toggle.onclick = function () {
   navigation.classList.toggle("active");
   main.classList.toggle("active");
 };

 const displayFormBtn = document.getElementById('displayFormBtn');
 const overlay = document.getElementById('overlay');
 const closeFormBtn = document.getElementById('closeFormBtn');

 displayFormBtn.addEventListener('click', () => {
     overlay.style.display = 'flex';
 });

 closeFormBtn.addEventListener('click', () => {
     overlay.style.display = 'none';
});

const displayFormBtnTask = document.getElementById('displayFormBtnTask');
 const overlayTask = document.getElementById('overlayTask');
 const closeFormBtnTask = document.getElementById('closeFormBtnTask');

 displayFormBtnTask.addEventListener('click', () => {
     overlayTask.style.display = 'flex';
 });

 closeFormBtnTask.addEventListener('click', () => {
     overlayTask.style.display = 'none';
});

//js for venue form

const addVenueBtn = document.getElementById('addVenueBtn');
  const formContainer = document.getElementById('form-containerr');

  addVenueBtn.addEventListener('click', function() {
    if (formContainer.style.display === 'none') {
      formContainer.style.display = 'block';
    } else {
      formContainer.style.display = 'none';
    }
  });

  //select js
  var x, i, j, l, ll, selElmnt, a, b, c;
/* Look for any elements with the class "custom-select": */
x = document.getElementsByClassName("formt");
l = x.length;
for (i = 0; i < l; i++) {
  selElmnt = x[i].getElementsByTagName("select")[0];
  ll = selElmnt.length;
  /* For each element, create a new DIV that will act as the selected item: */
  a = document.createElement("DIV");
  a.setAttribute("class", "select-selected");
  a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
  x[i].appendChild(a);
  /* For each element, create a new DIV that will contain the option list: */
  b = document.createElement("DIV");
  b.setAttribute("class", "select-items select-hide");
  for (j = 1; j < ll; j++) {
    /* For each option in the original select element,
    create a new DIV that will act as an option item: */
    c = document.createElement("DIV");
    c.innerHTML = selElmnt.options[j].innerHTML;
    c.addEventListener("click", function(e) {
        /* When an item is clicked, update the original select box,
        and the selected item: */
        var y, i, k, s, h, sl, yl;
        s = this.parentNode.parentNode.getElementsByTagName("select")[0];
        sl = s.length;
        h = this.parentNode.previousSibling;
        for (i = 0; i < sl; i++) {
          if (s.options[i].innerHTML == this.innerHTML) {
            s.selectedIndex = i;
            h.innerHTML = this.innerHTML;
            y = this.parentNode.getElementsByClassName("same-as-selected");
            yl = y.length;
            for (k = 0; k < yl; k++) {
              y[k].removeAttribute("class");
            }
            this.setAttribute("class", "same-as-selected");
            break;
          }
        }
        h.click();
    });
    b.appendChild(c);
  }
  x[i].appendChild(b);
  a.addEventListener("click", function(e) {
    /* When the select box is clicked, close any other select boxes,
    and open/close the current select box: */
    e.stopPropagation();
    closeAllSelect(this);
    this.nextSibling.classList.toggle("select-hide");
    this.classList.toggle("select-arrow-active");
  });
}

function closeAllSelect(elmnt) {
  /* A function that will close all select boxes in the document,
  except the current select box: */
  var x, y, i, xl, yl, arrNo = [];
  x = document.getElementsByClassName("select-items");
  y = document.getElementsByClassName("select-selected");
  xl = x.length;
  yl = y.length;
  for (i = 0; i < yl; i++) {
    if (elmnt == y[i]) {
      arrNo.push(i)
    } else {
      y[i].classList.remove("select-arrow-active");
    }
  }
  for (i = 0; i < xl; i++) {
    if (arrNo.indexOf(i)) {
      x[i].classList.add("select-hide");
    }
  }
}

/* If the user clicks anywhere outside the select box,
then close all select boxes: */
document.addEventListener("click", closeAllSelect);

function validateForm() {
  var hasErrors = document.querySelectorAll('.has-errors').length > 0;
  return !hasErrors; // Prevent form submission if there are errors
}