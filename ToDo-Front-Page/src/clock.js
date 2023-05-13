// const deg = 6;
// const hr = document.querySelector(".hr");
// const mn = document.querySelector(".mn");
// const sc = document.querySelector(".sc");

// setInterval(()=>{
//     let day = new Date();
//     let hh = day.getHours() * 30;
//     let mm = day.getMinutes() * deg;
//     let ss = day.getSeconds() * deg;
//     hr.style.transform = `rotateZ(${hh+(mm/12)}deg)`;
//     mn.style.transform = `rotateZ(${mm}deg)`;
//     sc.style.transform = `rotateZ(${ss}deg)`;
// });

const clock = document.querySelector('#clock');

function getClock(){
    const date = new Date();
    const hours = String(date.getHours()).padStart(2,"0");
    const minutes = String(date.getMinutes()).padStart(2,"0");
    const seconds = String(date.getSeconds()).padStart(2,"0");

    clock.innerText = (`${hours}:${minutes}:${seconds}`)
}

getClock() //최초 1회 실행
setInterval(getClock, 1000)