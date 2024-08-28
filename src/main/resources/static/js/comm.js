$(document).ready(function() {
    document.getElementById("answer").addEventListener("keypress", function(e){
        if(e.key == "Enter"){
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

  $('#sub-btn').click(function() {
  var ans = $('#answer').val();
        console.log("ans = "+ans);
        console.log("kan = "+kan);
                   if(ans == hira){
                      totalcorrect = totalcorrect + 1;
                      $('#total-correct').text(totalcorrect)
                      logToConsole("correct: "+kan+"　→　"+hira+"　→　"+mean);
                   }else{
                      totalfail = totalfail + 1;
                      $('#total-fail').text(totalfail)
                      logToConsole("fail: "+kan+"　→　"+hira+"　→　"+mean);
                   }
        document.getElementById('answer').value = '';
            $.ajax({
                url: '/api/random',
                method: 'GET',
                dataType: 'json',
                success: function(data) {
                    $('#kanji').text(data.kanji);
                    console.log(kan);
                    kan = data.kanji;
                    hira = data.hiragana;
                    mean = data.meaning;
                },
                error: function(xhr, status, error) {
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