$(document).ready(function () {
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
    } else {
        newParagraph.textContent = '✗';
        newParagraph.style.color = 'red';
        totalfail = totalfail + 1;
        $('#total-fail').text(totalfail)
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
    // Create a new paragraph element for the log entry
    const newLogEntry = document.createElement('p');
    newLogEntry.classList.add('log-entry');
    newLogEntry.textContent = `Log Entry #${counter}: ${new Date().toLocaleTimeString()} ${d}`;

    // Append the new log entry to the console div
    const consoleDiv = document.getElementById('console');
    consoleDiv.appendChild(newLogEntry);

    // Scroll the console div to the bottom to show the latest entry
    consoleDiv.scrollTop = consoleDiv.scrollHeight;

    // Increment the counter for the next log entry
    counter++;
}