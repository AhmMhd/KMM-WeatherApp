import SwiftUI
import data

struct ContentView: View {
	let greet = Greeting().greeting()
    let weatherReq = WeatherRequest(city: "Karachi")
    
    

	var body: some View {
		Text(greet)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
	ContentView()
	}
}
