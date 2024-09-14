$(document).ready(function () {
    getMultipleChoices();

    document.getElementById("answer").addEventListener("keypress", function (e) {
        if (e.key == "Enter") {
            e.preventDefault();
            document.getElementById("sub-btn").click();
        }
    });
});

var kan;
var hira;
var mean;
var totalcorrect = 0;
var totalfail = 0;
var temp_kan;
var temp_hira;
var temp_mean;

$('#hint-btn').click(function(){
    $('#hint-span').text(mean)
});

$('#sub-btn').click(function () {
    var ans = $('#answer').val();
    var newParagraph = document.createElement('p');
    document.getElementById('check').innerHTML = '';
    console.log("ans = " + ans);
    console.log("kan = " + kan);
    $('#hint-span').text('')

    if (ans == hira) {
        newParagraph.textContent = '✓';
        newParagraph.style.color = 'green';
        totalcorrect = totalcorrect + 1;
        $('#total-correct').text(totalcorrect)
        logToConsole("Correct："+kan+" = "+hira+" → "+mean);

    } else {
        newParagraph.textContent = '✗';
        newParagraph.style.color = 'red';
        totalfail = totalfail + 1;
        $('#total-fail').text(totalfail)
        logToConsole("Wrong："+kan+" = "+hira+" → "+mean);

    }

        newParagraph.style.fontSize = '18px';
        newParagraph.style.margin = '10px 0';

    temp_kan = kan;
    temp_hira = hira;
    temp_mean = mean;

$('#kan').text(temp_kan)
$('#hira').text(temp_hira)
$('#mean').text(temp_mean)

    document.getElementById('answer').value = '';
    document.getElementById('check').appendChild(newParagraph);

    $.ajax({
        url: '/api/random',
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            $('#kanji').text(data.kanji);
            console.log(kan);
            kan = data.kanji;
            hira = data.hiragana;
            mean = data.meaning;
        },
        error: function (xhr, status, error) {
            console.error('Error:', error);
        }

    });
});
let counter = 1;

function logToConsole(d) {
    const newLogEntry = document.createElement('p');
    newLogEntry.classList.add('log-entry');
    newLogEntry.textContent = `Log Entry #${counter}: ${new Date().toLocaleTimeString()} ${d}`;

    const consoleDiv = document.getElementById('console');
    consoleDiv.appendChild(newLogEntry);
    consoleDiv.scrollTop = consoleDiv.scrollHeight;
    counter++;
}

let answer;

function getMultipleChoices(){
    $.ajax({
        url: '/api/random',
        method: 'GET',
        dataType: 'json',
        success: function (data) {

            $('#eng-mean').text(data.hiragana);
            $('#kanji').text(data.kanji);
            $('#answer1').text(data.selection1);
            $('#answer2').text(data.selection2);
            $('#answer3').text(data.selection3);
            $('#answer4').text(data.selection4);
            console.log(data.kanji+":"+data.hiragana+":"+data.meaning);
            answer = data.meaning;
        },
        error: function (xhr, status, error) {
            console.error('Error:', error);
        }

    });
}

 // Function that will be executed for all buttons
 function buttonClicked(event) {
    let buttonId = event.target.getAttribute('data-id');
    let btnText = event.target.textContent;
    
    var newParagraph = document.createElement('p');
    document.getElementById('status').innerHTML = '';

    if(btnText === answer)
    {
        newParagraph.textContent = '✓';
        newParagraph.style.color = 'green';
        // alert("correct");
    }
    else
    {
        // alert("incorrect");
        newParagraph.textContent = '✗';
        newParagraph.style.color = 'red';
    }

    document.getElementById('status').appendChild(newParagraph);
    getMultipleChoices();
}

// Get all buttons and assign the same function
const buttons = document.querySelectorAll('button[data-id]');
buttons.forEach(button => {
    button.addEventListener('click', buttonClicked);
});