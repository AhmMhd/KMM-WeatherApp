import SwiftUI
import data
import Kingfisher

struct ContentView: View {
    
    let sometext = Greeting().greeting()
    
    @State var tempData: [TempData] = []
    
    
    @State var date : String = ""
    @State var temperature : String = ""
    @State var temperatureDescription : String = ""
    @State var iconURL : String = ""

    
    let layout = [GridItem(.adaptive(minimum: 100))]

    var body: some View {
        VStack(alignment: .center) {
            Text("Karachi").fontWeight(/*@START_MENU_TOKEN@*/.bold/*@END_MENU_TOKEN@*/).font(.system(size : 35))
            Text(date)
            Spacer()
            HStack {
                Text(temperature).fontWeight(/*@START_MENU_TOKEN@*/.bold/*@END_MENU_TOKEN@*/).font(.system(size: 100))
                KFImage(URL(string: iconURL)).frame(width: 50, height: 50)
            }
            Text(temperatureDescription)
            Spacer()
            ScrollView(.horizontal) {
                LazyHGrid(rows: layout, alignment: .center, spacing: 16) {
                    ForEach(tempData, id: \.id) { temp in
                        DayTileView(dayText: temp.dayText, imageUrlString: temp.imageUrlString, tempratureRange: temp.tempratureRange)
                    }
                }
            }.frame(height: 200)
        }
        .onAppear {
            
           FetchDailyForecastUC()
                .invoke(dailyForecastRequest: DailyForecastRequest(lat: 24.8607, lng: 67.0011))
                { res, error in
            
                    if(res is ResponseSuccess<DailyForecast>){
                  
                        let result = res as? ResponseSuccess<DailyForecast>
                        date = result?.response?.todayWeatherData?.day ?? ""
                        temperature = result?.response?.todayWeatherData?.temp ?? ""
                        temperatureDescription = result?.response?.todayWeatherData?.description_ ?? ""
                        iconURL = result?.response?.todayWeatherData?.icon ?? ""
                        
                        result?.response?.dailyWeatherData?.forEach({ data in
                            tempData.append(TempData(dayText: data.day, imageUrlString: data.icon ?? "", tempratureRange: (data.temp ?? "").appending("/").appending(data.feels_like ?? "")  ))
                        })
                    }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
	ContentView()
	}
}


struct DayTileView: View {
    let dayText: String
    let imageUrlString: String
    let tempratureRange: String
    var body: some View {
        VStack(alignment: .center, spacing: 25) {
            Text(dayText)
            KFImage(URL(string: imageUrlString)).frame(width: 10, height: 10)
            Text(tempratureRange)
        }
    }
}


struct TempData {
    var id = UUID().uuidString
    let dayText: String
    let imageUrlString: String
    let tempratureRange: String
}
