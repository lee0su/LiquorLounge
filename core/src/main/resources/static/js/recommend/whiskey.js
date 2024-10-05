const question = document.querySelector(".question-div");
const answerOne = document.querySelector(".answer.one");
const answerTwo = document.querySelector(".answer.two");
const exitButton = document.querySelector(".exit");

const blockDiv = document.querySelector(".block");
const recommendWhiskeyContainer = document.querySelector(".recommend-whiskey-container")
const recommendWhiskeyAnswerContainer = document.querySelector(".recommend-whiskey-answer-container");

let answers = [];
let count = 0;

// 질문 목록
const questions = ["question 2", "question 3", "question 4", "question 5", "question 6"];

// 답변 목록 1번버튼 (윗쪽)
const answerOneText = ["answer 1-2", "answer 1-3", "answer 1-4", "answer 1-5", "answer 1-6"];
// 답변 목록 2번버튼 (아랫쪽)
const answerTwoText = ["answer 2-2", "answer 2-3", "answer 2-4", "answer 2-5", "answer 2-6"];

function setWhiskeyQuestion(answer) {

    answers.push(answer);
    console.log(answers);
    console.log(count);

    if (count !== questions.length) {
        question.innerText = questions[count];
        answerOne.innerText = answerOneText[count];
        answerTwo.innerText = answerTwoText[count];
    }

    if (count === questions.length) {
        fetch('/api/recommend/whiskey', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(answers),
        })
            .then(response => response.json())
            .then(data => {
                recommendWhiskeyContainer.style.display = 'none';
                blockDiv.style.display = 'flex';

                setTimeout(() => {
                    blockDiv.style.display = 'none';

                    recommendWhiskeyAnswerContainer.style.display = 'flex';

                    recommendWhiskeyAnswerContainer.innerHTML = `
                    <div class="recommended-whiskey-box">
                        <div class="whiskey-img-box">
                            <img class="recommend-whiskey-img" src="${data.img}" alt="${data.name}">
                        </div>
                        <div class="recommended-whiskey-name-proof">
                            <div class="recommend-whiskey-name">${data.name}</div>
                            <div class="recommend-whiskey-proof">ABV: ${(data.proof / 2).toFixed(1)}%</div>
                        </div>
                    </div>
                    <div class="recommend-whiskey-explanation">Explanation</div>
                    <div class="recommend-whiskey-price">70cl $${data.pricePer70cl}</div>
                    <div class="button-box">
                        <button class="exit" onclick="location.href='/'">Main</button>
                        <button class="more-info" onclick="location.href='/'">More info</button>
                    </div>
                    `;
                }, 3000);

            })
            .catch((error) => {
                console.error(error);
            });

    }

    count++;

}
