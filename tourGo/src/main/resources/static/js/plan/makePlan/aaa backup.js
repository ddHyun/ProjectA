const body = document.querySelector('body');
const modal = document.querySelector('.modal');
    const boxes=document.querySelectorAll('.grid-container');
    boxes.forEach( (griditem) => {
   griditem.addEventListener('click', () => {
  modal.classList.toggle('show');
    if (modal.classList.contains('show')) {
    body.style.overflow = 'hidden';
  }
   })
 })




modal.addEventListener('click', (event) => {
  if (event.target === modal) {
    modal.classList.toggle('show');

    if (!modal.classList.contains('show')) {
      body.style.overflow = 'auto';
    }
  }
});