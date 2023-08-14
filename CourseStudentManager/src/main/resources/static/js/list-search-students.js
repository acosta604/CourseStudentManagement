const tableBody = document.getElementById('tableBody');
const searchInput = document.getElementById('search');
const searchButton = document.getElementById('searchButton');
const resultCount = document.getElementById('resultCount');

let students = []; // Almacenar todos los estudiantes

const listStudents = async () => {
    try {
        const response = await fetch('http://localhost:8080/api/student');
        students = await response.json(); // Almacenar estudiantes en la variable

        let content = '';
        students.forEach(student => {
            content += `
            <tr>
            <td>${student.id}</td>
            <td class="name-cell">${student.name}</td>
            <td class="email-cell">${student.email}</td>
            <td class="phone-cell">${student.phone}</td>
            <td class="options-cell">
                <a href="edit-student.html?id=${student.id}" class="edit-button" data-student-id="${student.id}">
                <img src="icons/edit.png" alt="Edit">
            </a>
            <a class="delete-button" data-student-id="${student.id}">
                <img src="icons/xxxx.png" alt="Delete">
            </a>
            <a href="add-student.html" class="add-button" id="showAddFormButton">
    <img src="icons/add.png" alt="Add">
</a>
          
            </td>
        </tr>`;
        });

        tableBody.innerHTML = content;
    } catch (error) {
        console.error('Error al cargar estudiantes:', error);
    }
};

const filterAndDisplayStudents = () => {
    const searchTerm = searchInput.value.toLowerCase();
    const filteredStudents = students.filter(student => {
        return student.name.toLowerCase().includes(searchTerm) || student.id.toString().includes(searchTerm);
    });

    let content = '';

    if (filteredStudents.length === 0) {
        content = `
            <tr>
                <td colspan="5">No student were found</td>
            </tr>`;
    } else {
        filteredStudents.forEach(student => {
            content += `
            <tr>
            <td>${student.id}</td>
            <td class="name-cell">${student.name}</td>
            <td class="email-cell">${student.email}</td>
            <td class="phone-cell">${student.phone}</td>
            <td class="options-cell">
                <a  href="edit-student.html?id=${student.id}" class="edit-button" data-student-id="${student.id}">
    <img src="icons/edit.png" alt="Edit" >
</a>
<a class="delete-button" data-student-id="${student.id}">
    <img src="icons/xxxx.png" alt="Delete">
</a>
<a href="add-student.html" class="add-button" id="showAddFormButton">
    <img src="icons/add.png" alt="Add">
</a>

          
            </td>
        </tr>`;
        });
    }

    tableBody.innerHTML = content;
    resultCount.textContent = `Search results: ${filteredStudents.length}`;
};




// Agregar evento de clic al botón de búsqueda
searchButton.addEventListener('click', filterAndDisplayStudents);

// Agregar evento de cambio en el campo de búsqueda
searchInput.addEventListener('input', filterAndDisplayStudents);

// Llamar a la función para cargar la lista de estudiantes al cargar la página
window.addEventListener('load', async () => {
    await listStudents();
});





