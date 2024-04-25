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