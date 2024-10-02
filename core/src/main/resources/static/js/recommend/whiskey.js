const question = document.querySelector(".question-div");
const answerOne = document.querySelector(".answer.one");
const answerTwo = document.querySelector(".answer.two");

let answers = [];
let count = 0;

const questions = ["question 2", "question 3", "question 4", "question 5", "question 6"];

function setQuestion(answer) {

    answers.push(answer);
    console.log(answers);
    console.log(count);

    if (count !== questions.length) {
        question.innerText = questions[count];
    }

    if (count === questions.length) {
        question.innerText = 'END !';
        answerOne.style.display = 'none';
        answerTwo.style.display = 'none';
    }

    count++;

}
