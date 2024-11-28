<form method="post" action="http://localhost:8080/signup">
    <fieldset style="width: 150px;">
        <legend>회원가입</legend>
        
        <label for="id">ID:</label>
        <input type="text" id="id" name="id" required>

        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" required>

        <label for="username">닉네임:</label>
        <input type="text" id="username" name="username" required>

        <label for="email">이메일:</label>
        <input type="email" id="email" name="email" required>

        <button type="submit">제출</button>
    </fieldset>
</form>
