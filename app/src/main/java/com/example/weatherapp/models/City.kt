//package com.example.weatherapp.models
//
//
//class City(var id: Int, var name: String, var coord: Coord,
//           var country: String, var cod: String, var message: String,
//           var cnt: Int, list: List<list>) {
//
//}
//
//class Coord(var longitude: Double, var latitude: Double) {
//
//}
//
//class Main(var temp: Double, var temp_min: Double, var temp_max: Double, var pressure: Double,
//           var sea_level: Double, var grnd_level, var humidity: Int, var temp_kf: Double) {
//
//}
//
//public class Weather(id: Int, ) {
//    public int id
//    { get; set; }
//    public string main
//    { get; set; }
//    public string description
//    { get; set; }
//    public string icon
//    { get; set; }
//}
//
//public class Clouds {
//    public int all
//    { get; set; }
//}
//
//public class Wind {
//    public double speed
//    { get; set; }
//    public double deg
//    { get; set; }
//}
//
//public class Sys {
//    public string pod
//    { get; set; }
//}
//
//public class List {
//    public int dt
//    { get; set; }
//    public Main main
//    { get; set; }
//    public List<Weather> weather
//    { get; set; }
//    public Clouds clouds
//    { get; set; }
//    public Wind wind
//    { get; set; }
//    public Sys sys
//    { get; set; }
//    public string dt_txt
//    { get; set; }
//}
//
//
//public class RootObject {
//    public City city
//    { get; set; }
//}