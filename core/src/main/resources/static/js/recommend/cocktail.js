const question = document.querySelector(".question-div");
const answerOne = document.querySelector(".answer.one");
const answerTwo = document.querySelector(".answer.two");
const exitButton = document.querySelector(".exit");

let answers = [];
let count = 0;

// 질문 목록
const questions = ["question 2", "question 3", "question 4", "question 5", "question 6"];

// 답변 목록 1번버튼 (윗쪽)
const answerOneText = ["answer 1-2", "answer 1-3", "answer 1-4", "answer 1-5", "answer 1-6"];
// 답변 목록 2번버튼 (아랫쪽)
const answerTwoText = ["answer 2-2", "answer 2-3", "answer 2-4", "answer 2-5", "answer 2-6"];

function setQuestion(answer) {

    answers.push(answer);
    console.log(answers);
    console.log(count);

    if (count !== questions.length) {
        question.innerText = questions[count];
        answerOne.innerText = answerOneText[count];
        answerTwo.innerText = answerTwoText[count];
    }

    if (count === questions.length) {
        question.innerText = 'END !';
        answerOne.style.display = 'none';
        answerTwo.style.display = 'none';
        exitButton.style.display = 'flex';
    }

    count++;

}
