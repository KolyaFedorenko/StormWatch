# WatchStorm
[![CodeFactor](https://www.codefactor.io/repository/github/kolyafedorenko/stormwatch/badge)](https://www.codefactor.io/repository/github/kolyafedorenko/stormwatch)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/KolyaFedorenko/StormWatch.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/KolyaFedorenko/StormWatch/context:java)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/KolyaFedorenko/StormWatch.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/KolyaFedorenko/StormWatch/alerts/)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/KolyaFedorenko/StormWatch?color=brighteen)
![GitHub Release Date](https://img.shields.io/github/release-date/KolyaFedorenko/StormWatch)

WatchStorm is a mobile app for adding ratings to movies or TV shows you've watched

### Features of WatchStorm:
- Storing all data in Firebase Realtime Database and Firebase Storage
- Automatic movie search by title
- Score movies on three dimensions: plot, cast and visuals
- Calculating the average user rating for each movie
- Displaying the average audience rating of a movie if the movie was found via search
- Verification system that allows the most active users to verify their account

### Libraries used
| Used library | Used to |
| ------------ | ----------- |
| [airbnb/lottie-android](https://github.com/airbnb/lottie-android) | Display Lottie animations |
| [Dhaval2404/ImagePicker](https://github.com/Dhaval2404/ImagePicker) | Add custom movie posters |
| [hdodenhof/CircleImageView](https://github.com/hdodenhof/CircleImageView) | Display movie posters in a circle |
| [bumptech/glide](https://github.com/bumptech/glide) | Load images by URL and insert them into ImageViews |
| [google/volley](https://github.com/google/volley) | Send GET requests and receive JSON responses |
| [google/gson](https://github.com/google/gson) | Serialize and deserialize JSON |

### The code uses
In addition to the usual classes, the code also uses
- Interfaces
- Abstract classes
- Custom Views
