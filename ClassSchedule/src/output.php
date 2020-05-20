<?php print_r($_FILES);


//Makes sure this only runs when there is a POST request
if($_SERVER['REQUEST_METHOD'] === 'POST') 
{
		//Check to see if the files went through
		if(isset($_FILES['files']))
		{
			$errors = [];				//an array for errors
			$path = 'uploads/';			//file path of the uploads folder

			//accepted extensions for the input files --- talk about this in Presentation
			//$extensions = ['.csv'];		

			//gets the total number of files being uploaded by the user and loops through them
			$all_files = count($_FILES['files']['tmp_name']);

			for($i = 0; $i < $all_files; $i++)
			{
				$file_name = $_FILES['files']['name'][$i];		//gets the name of the file
				$file_tmp = $_FILES['files']['tmp_name'][$i];		//creates a temp file
				$file_type = $_FILES['files']['type'][$i];		//gets the file type
				$file_size = $_FILES['files']['size'][$i];		//gets the file size

				//gets the extension of the file
				//$file_ext = strtolower(end(explode('.', $_FILES['files']['name'][$i])));	

				$file = $path . $file_name;

				//checks to see if the file's extension is not in the approved aaray of extensions
				//if(!in_array($file_ext, $extensions))
				//{
					//$errors[] = 'Extension is not allowed. You inputed: ' . $file_name . ' ' . $file_type;
				//}

				//checks to see if the size of the file is too large, in our case I decided to make the size 100MB just to be safe, however, I don't believe any of the files that may be used will exceed this limit
				if($file_size > 104857600)
				{
					$errors[] = 'File size is too big: ' . $file_name . ' ' . $file_type;
				}

				//if there are no errors, then move the file to the upload folder
				if(empty($errors))
				{
					move_uploaded_file($file_tmp, $file);
				}
			}

			//if there were any errors with the files, then the error summary will print out
			if($errors)
			{
				print_r($errors);
			}
		}
}
?>