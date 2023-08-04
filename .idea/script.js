const addGradeButtons = document.querySelectorAll('.btn-add-grade');

addGradeButtons.forEach(button => {
  button.addEventListener('click', () => {
    const gradesContainer = button.nextElementSibling;
    const gradeValue = prompt('Ingrese la calificación:');
    
    if (gradeValue !== null && gradeValue !== '') {
      const gradeElement = document.createElement('div');
      gradeElement.classList.add('grade');
      gradeElement.innerHTML = `<span>Calificación:</span><span>${gradeValue}</span>`;
      gradesContainer.appendChild(gradeElement);
    }
  });
});
