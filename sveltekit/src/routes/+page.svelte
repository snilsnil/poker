<!-- <h1>poker game</h1> -->

<script>
    import axios from "axios";
    let message = '';
    let password = '';
    let username = '';

    /**
     * @param {{ preventDefault: () => void; }} event
     */
    async function submitButton(event) {
        event.preventDefault();

        if (password !== '' && username !== '') {
            await signin();
        } else {
            message = '빈칸이 존재합니다.';
        }
    }

    async function signin() {
        try {
            const response = await axios.post('http://localhost:8080/login', {
                username,
                password
            }, {
                withCredentials: true // 쿠키와 함께 요청을 보냅니다.
            });

            if (response.status === 200) {
                const token = response.headers['authorization'];
                console.log(token);
                if (token) {
                    const tokenValue = token.replace('Bearer ', '');
                    document.cookie = `token=${tokenValue}; path=/;`;
                    return window.location.href = '/game';
                } else {
                    message = "토큰을 받지 못했습니다.";
                }
            } else {
                message = "문제가 발생했습니다.";
            }
        } catch (error) {
            console.error(error);
            message = "문제가 발생했습니다.";
        }
    }
</script>

<fieldset style="width: 150px;">
    <form on:submit={submitButton}>
        <legend>로그인</legend>

        <span style="color: {message === '' ? 'black' : 'red'}">
            {message}
        </span>
        <br>
        <br>

        <label for="username">ID:</label>
        <input type="text" bind:value={username} id="username" name="username" required>

        <label for="password">비밀번호:</label>
        <input type="password" bind:value={password} id="password" name="password" required>

        <button type="submit">로그인</button>
    </form>
    <br>
    <text>회원이 아니신가요?</text>
    <button on:click={() => window.location.href = '/signup'}>회원가입</button>
</fieldset>
