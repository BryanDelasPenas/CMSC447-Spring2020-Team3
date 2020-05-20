# File         get_distances.py
# Author       Bryan Delas Penas 
# Email        bdelasp1@umbc.edu 
# Date:        04/21/20
# Description: This program is broken down into 4 steps 
#              1. Reads in the buildings from the file classroom.csv  
#              2. Get the longtiude and latidue from the building name
#              3. Calculate distance from building name from ITE buliding 
#              4. Write step three into a CSV named distances.

import csv
import os

from geopy.geocoders import Nominatim
from geopy import distance

# Function: read_building 
# Input  -  None
# Output -  Returns a list of building names 
def read_building(): 
    # Get the file path that is used, reads in Classroom.csv in another directory 
    script_path = os.path.dirname(__file__)
    script_dir = os.path.split(script_path)[0]
    rel_path = r"CSV data files\Classroom.csv"
    abs_file_path = os.path.join(script_dir, rel_path)
    
    try:
        # Opens the file and creates the reader 
        file_name = open(abs_file_path)
        file_reader = csv.reader(file_name)

        # Create the temp list and building list 
        temp_list = [] 
        building_list = []
    
        # Adds the reading output to a temp list 
        for row in file_reader: 
            temp_list.append(row)
    
        # For loop to get only buildings and remove 
        for i in range(1, len(temp_list)):
            building_name = temp_list[i][0]

            # Removes rooms leaving only buildings and lower cases it
            new_string = ''.join([j for j in building_name if not j.isdigit()])
            new_string = new_string.strip()
        
            # This hard coded in, As there is no way to check if the building is valid or not. There is only 14 building in UMBC 
            # that has rooms that can hold a capacity of 30. If there is another way to impliment this, I will look for another way. 
      
            # Special case if it contains a comma, usally only engineering 122 , engineering 122A building will have this
            if("," in new_string and new_string not in building_list):
                string_cut = new_string.split()
                building_list.append(string_cut[0])        
            elif(new_string in building_list):
                pass
            else:
                building_list.append(new_string)        
  
        file_name.close
        # returns the buildiing list
        return building_list 
    except:
        raise Exception("File is not found, please input the right file")
# Function: get_cordinates
# Input  -  Takes in a list of building names 
# Output -  Returns a list of tuples where it contains valid (long, lad)
def get_cordinate(building_list):
    
    # Initalize the cordinate_list
    cordinate_list = []
    
    locator = Nominatim(user_agent="myGeocoder")

    # Iterate through teh list 
    for i in range(len(building_list)):

        try:
            # Initalize the tuple in the for loop
            cordinate_tuple = tuple()
        
            location = locator.geocode(building_list[i] + ", Catonsville, Maryland")
            cordinate_tuple = (location.latitude, location.longitude)
            cordinate_list.append(cordinate_tuple) 
        
        except:
            raise Exception("That is not a valid location")
   
    # return the list 
    return cordinate_list

# Function: calculate_distance 
# Input  -  Takes in a list of tuples of (long, lad)  
# Output -  Returns a list of distances 
def calculate_distance(cordinate_list):
    
    # Initalize the distance list
    distance_list = []
    
    # Get the location of ITE
    locator = Nominatim(user_agent="myGeocoder")
    location_ite = locator.geocode("Information Technology/Engineering, Baltimore, Maryland")
    location_tuple = (location_ite.latitude, location_ite.longitude)
    
    # Iterate throughout the whole list
    for i in range(len(cordinate_list)):
        
        # Get the distances 
        location_building = cordinate_list[i]
        if(distance.distance(location_building, location_tuple).km in distance_list):
            pass
        else:
            distance_list.append(distance.distance(location_building, location_tuple).km)
        
    return distance_list
    
# Function: write_csv
# Input  -  Takes in a list of distances and building list 
# Output -  None 
def write_csv(building_list, distance_list):
    
    # Get the file path that is used, reads in Classroom.csv in another directory 
    script_path = os.path.dirname(__file__)
    script_dir = os.path.split(script_path)[0]
    rel_path = r"CSV data files\Distance_from_ITE.csv"
    abs_file_path = os.path.join(script_dir, rel_path)
    
    # Headings for the CSV file
    field_list = ['Building Name', 'Distance from ITE']

    # Open the file 
    file_name = open(abs_file_path, mode='w',newline='')
    file_writer = csv.writer(file_name)
    file_writer.writerow(field_list)
    # Iterate through out both list 
    for i in range(len(distance_list)):
        file_writer.writerow([building_list[i], distance_list[i]])

    return 0

# Function main
# Input  - None
# Output - None 
def main():
    
    # Intilize the list 
    building_list = []
    cordinate_list = []
    distance_list = []
    
    # Call read_building 
    building_list = read_building() 
    
    # Call get_cordinate
    cordinate_list = get_cordinate(building_list)

    # Call calulate_distance
    distance_list = calculate_distance(cordinate_list)
   
    # Call write_csv
    write_csv(building_list,distance_list)

    # Print out for testing purposes
    #print(building_list)
    #print(cordinate_list)
    #print(distance_list)
    pass

if __name__ == "__main__":
    main()
