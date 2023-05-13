# todo_List_Project

## 메인 화면
![todolist](https://user-images.githubusercontent.com/96467897/159127742-f6845328-ed2a-45fb-8659-31e79fd9df0d.PNG)  
[참고 자료] NomadCoder JS챌린지 졸업생 mizzu님의 CSS / Yusuf Shakeel jsCalendar js


### 1. 로그인
```javascript
function onLoginSubmit(event) {
    event.preventDefault();
    modal.style.display = "none";
    const username = loginInput.value;
    localStorage.setItem(USERNAME_KEY, username);
    paintGreetings(username);
}
```
-> 사용자에게 입력 받은 이름을 **localStorage**에 보관하여 출력하도록 구현

### 2. 투두 리스트
#### 저장
```javascript
function handleToDoSubmit(event) {
    event.preventDefault();
    const newTodo = toDoInput.value;
    toDoInput.value = "";
    const newTodoObj = {
        text: newTodo,
        id: Date.now(),
    };
    toDos.push(newTodoObj);
    paintToDo(newTodoObj);
    saveToDos();
}

const savedToDos = localStorage.getItem(TODOS_KEY);
if (savedToDos !== null) {
    const parsedToDos = JSON.parse(savedToDos);
    toDos = parsedToDos;
    parsedToDos.forEach(paintToDo);
}
```
-> 로그인과 동일하게 작성된 투두리스트를 **localStorage**에 객체 형태로 보관  
해당 입력값은 JSON.stringify <-> JSON.parse 를 통해 저장하고 불러오기  

#### 삭제
```javascript
function deleteToDo(event) {
    const li = event.target.parentElement;
    li.remove();
    toDos = toDos.filter((toDo) => toDo.id !== parseInt(li.id));
    saveToDos();
}
```
삭제는 filter함수를 이용하여 구현  
filter함수를 통해 선택된 li만 제외하여 새로운 리스트를 형성한 후, 해당 리스트를 다시 localStorage에 저장  
이 때, localStrage에 있는 id값은 number, li에 저장된 id값은 String임을 주의하여 작성  

### 3. 인용문
```javascript
const quote = document.querySelector(".text");
const author = document.querySelector(".author");

const random = quotes[Math.floor(Math.random()*quotes.length)];

quote.innerText = random.quote;
author.innerText = random.author;
```
저장된 인용구문을 Math.random()함수를 이용하여 랜덤으로 출력할 수 있도록 구현  

### 4. 달력
dyCalendar is a JavaScript library for creating Calendar.

Author: Yusuf Shakeel
https://github.com/yusufshakeel

GitHub Link: https://github.com/yusufshakeel/dyCalendarJS

MIT license
Copyright (c) 2016 Yusuf Shakeel
해당 링크를 참고하여 코드를 첨부하여 구현

### 5. 날씨 API
https://openweathermap.org/api API 활용

```javascript
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

```
