<fieldset style="width: 150px;">
    <form method="post" action="http://localhost:8080/login">
        
            <legend>로그인</legend>
            
            <label for="userId">ID:</label>
            <input type="text" id="userId" name="userId" required>

            <label for="password">비밀번호:</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">로그인</button>
    </form>
    <br>
    <text>회원이 아니신가요?</text>
            <button on:click={()=>window.location.href='/signup'}>회원가입</button>
</fieldset>
