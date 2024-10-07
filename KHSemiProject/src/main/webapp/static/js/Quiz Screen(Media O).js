function startTimer(duration) {
    const progressBar = document.getElementById('progress');
    const timerValue = document.getElementById('timer-value');
    let remainingTime = duration;

    progressBar.style.width = '100%'; 
    timerValue.textContent = remainingTime; 

    const interval = setInterval(() => {
        if (remainingTime > 0) {
            remainingTime--; 
            const percentage = (remainingTime / duration) * 100;
            progressBar.style.width = `${percentage}%`; 

            timerValue.textContent = remainingTime; 
            console.log(`남은 시간: ${remainingTime}, 진행 바 너비: ${progressBar.style.width}`);
        }

        if (remainingTime <= 0) {
            clearInterval(interval); 
            console.log('시간이 다됐습니다!');
        }
    }, 1000);
}


startTimer(30);
