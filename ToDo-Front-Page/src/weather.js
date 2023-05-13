const API_KEY = "994d839bf0ada4cde54bebc3a4dbc171";

function onGeoOk(position) {
    const lat = position.coords.latitude;
    const lon = position.coords.longitude;;
    const url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}&units=metric`;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            const weatherImg = document.querySelector(".weatherIcon");
                 weatherImg.src = `https://openweathermap.org/img/wn/${data.weather[0].icon}.png`;

                 console.log(weatherImg);

                 document.querySelector("#json > ul > li:nth-child(2) > div > ul > li > div > ul > li:nth-child(2) > div > span.type-string")

            const temp = document.querySelector(".temp");
            temp.innerText = `${data.main.temp}°C`;

            const city = document.querySelector(".city");
            city.innerText = data.name;
        });
}

function onGeoError() {
    alert("Can't find you.");
}

navigator.geolocation.getCurrentPosition(onGeoOk, onGeoError);