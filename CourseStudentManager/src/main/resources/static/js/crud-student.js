

//EDIT STUDENT
document.addEventListener('DOMContentLoaded', async () => {
    const studentIdInput = document.getElementById('studentId');
    const nameInput = document.getElementById('name');
    const emailInput = document.getElementById('email');
    const phoneInput = document.getElementById('phone');

    // Obtiene los parámetros de la URL
    const urlParams = new URLSearchParams(window.location.search);
    const studentId = urlParams.get('id');

    if (studentId === null) {
       // console.error('No se proporcionó un ID de estudiante en la URL');
    } else {
       // console.log('Student ID:', studentId);

        try {
            const response = await fetch(`http://localhost:8080/api/student/${studentId}`);
            const data = await response.json();

            // Llena los campos con los datos del estudiante
            studentIdInput.value = data.id;
            nameInput.value = data.name;
            emailInput.value = data.email;
            phoneInput.value = data.phone;
        } catch (error) {
            console.error('Error en la solicitud GET:', error);
        }
    }
    // ...


// ...

    // Agrega el evento al formulario para realizar la actualización
    const editForm = document.getElementById('editForm');
    editForm.addEventListener('submit', async (event) => {
        event.preventDefault(); // Evitar el envío predeterminado del formulario
        
        // Obtiene los valores de los campos del formulario
        const updatedName = nameInput.value;
        const updatedEmail = emailInput.value;
        const updatedPhone = phoneInput.value;
        
        // Crea un objeto con los datos actualizados
        const updatedStudent = {
            id: studentId,
            name: updatedName,
            email: updatedEmail,
            phone: updatedPhone
        };
        
        try {
            const response = await fetch(`http://localhost:8080/api/student/${studentId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedStudent)
            });
            
            if (response.ok) {
                console.log('Estudiante actualizado exitosamente');
                alert("Estudiante actualizado exitosamente");
             
            } else {
                console.error('Error al actualizar el estudiante');
            }
        } catch (error) {
            console.error('Error en la solicitud PUT:', error);
        }
    });
});

//-----------------------------------------
//DELETE STUDENT
//-----------------------------------------
$(document).ready(function () {
    const tableBody = $('#tableBody');
    const resultCount = $('#resultCount');

      // Agregar evento de clic a los botones de eliminación (delegación de eventos)
      tableBody.on('click', '.delete-button', async function (event) {
        const studentId = $(this).data('student-id');

        if (!confirm('¿Desea eliminar a este estudiante?')) {
            return;
        }

        try {
            await eliminarEstudiante(studentId);
            await listStudents(); // Vuelve a cargar la lista de estudiantes después de la eliminación
        } catch (error) {
            console.error('Error al eliminar estudiante:', error);
        }
    });

    // Función para cargar la lista de estudiantes
    function listStudents() {
        $.get('http://localhost:8080/api/student', function (data) {
            students = data;
            renderStudents();
        });
    }

    // Función para renderizar la lista de estudiantes
    function renderStudents() {
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

        tableBody.html(content);
        resultCount.text(`Resultados: ${students.length}`);
    }

    // Función para eliminar un estudiante
    async function eliminarEstudiante(id) {
        try {
            await $.ajax({
                url: `http://localhost:8080/api/student/${id}`,
                type: 'DELETE',
                success: function(response) {
                    console.log('Estudiante eliminado:', response);
                  
                }
            });
        } catch (error) {
            throw error;
        }
    }
});

//------------------------------------
//AGREGAR ESTUDIANTE
//------------------------------------
$(document).ready(function () {
    $("#addFormContainer").submit(function (event) {
        event.preventDefault(); // Evitar el envío por defecto del formulario
        
        // Obtener los valores de los campos del formulario
        const name = $("#name").val();
        const email = $("#email").val();
        const phone = $("#phone").val();
        
        // Crear un objeto con los datos del estudiante
        const studentData = {
            name: name,
            email: email,
            phone: phone
        };
        
        // Enviar la solicitud POST al servidor
        $.ajax({
            url: 'http://localhost:8080/api/student',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(studentData),
            success: function (data) {
                // Aquí puedes manejar la respuesta del servidor
                console.log('Estudiante agregado:', data);
                alert("Estudiante añadido");
            },
            error: function (error) {
                console.error('Error al agregar estudiante:', error);
                alert('Hubo un error al agregar el estudiante. Por favor, intenta nuevamente.');
            }
        });
        
    });
});


