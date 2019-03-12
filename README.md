# Game-Changers


An Android application designed to list down slack users from given team.


## Concepts and code structure:
- **View** : Consists of UI specific code like activities, fragments. Basically, glue classes for platform provided UI.
- **ViewModel** : consists of ViewModel classes from android architectural components
- **Model** : Data classes
- **Data Source** : consists of different types of data providers like local or web service.


## Libraries used

From android support library cardview, recyclerview and constraint-layout are used.
1. **gson** - serialization of json objects into java classes and vice versa
3. **glide** - fetching images from remote servers and efficient caching mechanism
4. **recyclerview** - Shows list in resource efficient way
5. **volley** - Perform network query


## Considerations
Apart from positive test cases, below use cases are considered for development and tested
1. Retaining state of UI after device rotation
2. Efficient retrieval of the data from server
3. Handling of parallel network queries from client


## Testing
1. Used provider pattern managing dependencies
2. Sample unit test and instrumentaiton test cases are added


## Known Issues
TBD
