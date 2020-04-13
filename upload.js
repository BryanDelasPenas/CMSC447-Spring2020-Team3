const url = 'output.php'
const form = document.querySelector('form')

//Listener for the form submit
form.addEventListener('submit', e => {
  e.preventDefault()

  //Gets all of the files being uploaded
  const files = document.querySelector('[type=file]').files
  const formData = new FormData()

  //Appends all of the files to the file array
  for (let i = 0; i < files.length; i++) {
    let file = files[i]

    formData.append('files[]', file)
  }

  fetch(url, {
    method: 'POST',
    body: formData,
  }).then(response => {
    console.log(response)
  })
})