<script>
    import axios from 'axios';
    let userId = '';
    let password1 = '';
    let password2 = '';
    let username = '';
    let email = '';

    let equalPassword = '';

    $: equalPassword = password1 === '' || password2 === ''
        ? "비밀번호를 입력하세요"
        : password1 === password2
        ? '비밀번호가 일치합니다.'
        : '비밀번호가 일치하지 않습니다.';

    /**
   * @param {{ preventDefault: () => void; }} event
   */
    async function submitButton(event) {
        event.preventDefault();

        if (password1 === password2 && userId !== '' && username !== '' && email !== '') {
            try {
                const response = await axios.post('http://localhost:8080/signup', {
                    userId,
                    password: password1,
                    username,
                    email
                }, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
                alert('회원가입 성공!');
                window.location.href = '/'; // 성공 시 리다이렉트
            }  catch (error) {
                if (axios.isAxiosError(error)) { // AxiosError인지 확인
                    console.error('회원가입 실패', error.response?.data || error.message);
                    alert(`회원가입 실패: ${error.response?.data || error.message}`);
                } else {
                    console.error('회원가입 실패', error);
                    alert('알 수 없는 오류가 발생했습니다.');
                }
            }
        } else {
            alert('비밀번호가 일치하지 않거나 빈 필드가 있습니다.');
        }
    }

</script>

<form on:submit={submitButton}>
    <fieldset style="width: 150px;">
        <legend>회원가입</legend>

        <label for="userId">ID:</label>
        <input type="text" bind:value={userId} id="userId" name="userId" required>

        <label for="password1">비밀번호:</label>
        <input type="password" bind:value={password1} id="password1" name="password" required>

        <label for="password2">비밀번호 확인:</label>
        <input type="password" bind:value={password2} id="password2" required>

        <br>
        <br>
        <span style="color: {password1 === '' || password2 === '' ? 'black' : password1 === password2 ? 'green' : 'red'}">{equalPassword}</span>
        <br>
        <br>

        <label for="username">닉네임:</label>
        <input type="text" bind:value={username} id="username" name="username" required>

        <label for="email">이메일:</label>
        <input type="email" bind:value={email} id="email" name="email" required>

        <button type="submit">회원가입</button>
    </fieldset>
</form>
