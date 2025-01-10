<script>
    import axios from 'axios';
    let userId = '';
    let password1 = '';
    let password2 = '';
    let username = '';
    let email = '';
    let message = '';

    let equalPassword = '';

    $: checkPassword=password1===''
    ?"소문자, 숫자, 특수문자 포함해서 8~15자리 입력하세요."
    :CheckPassword() == true
    ? "모든 조건이 충족되었습니다."
    : "조건이 충족되지 못했습니다.";

    $: equalPassword = password1 === '' || password2 === ''
        ? "비밀번호를 입력하세요."
        : password1 === password2
        ? '비밀번호가 일치합니다.'
        : '비밀번호가 일치하지 않습니다.';

    function CheckPassword(){
        const checks = {
            lowerCase: /[a-z]/.test(password1),
            number: /[0-9]/.test(password1),
            special: /[~!@#$%^&*()_+|<>?:{}`]/.test(password1),
            length: password1.length
        };
        return checks.lowerCase
        && checks.number && checks.special && 
        7 < checks.length && checks.length < 15
    };

    /**
   * @param {{ preventDefault: () => void; }} event
   */
    async function submitButton(event) {
        event.preventDefault();

        if (CheckPassword() && password1 === password2 && userId !== '' && username !== '' && email !== '') {
                await createUser();
        } else if (!CheckPassword() || password1 !== password2){
            message = '비밀번호 조건이 맞지 않습니다.'
        } 
        else {
            message = '빈칸이 존재합니다.';
        }
    }

    async function createUser(){
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

        if(response.data=='success'){
            window.location.href = '/'; // 성공 시 리다이렉트
        } else 
        message = response.data;
    }

</script>

<form on:submit={submitButton}>
    <fieldset style="width: 450px;">
        <legend>회원가입</legend>

        <span style="color: {message === ''? 'black' : 'red'}">
            {message}
        </span>
        <br>
        <br>

        <label for="userId">ID:</label>
        <input type="text" bind:value={userId} id="userId" name="userId" required>
        <br>

        <label for="password1">비밀번호:</label>
        <input type="password" bind:value={password1} id="password1" name="password" required>
        <br>

        <label for="password2">비밀번호 확인:</label>
        <input type="password" bind:value={password2} id="password2" required>
        <br>

        <br><br>
        <ul>
            <li>
                <span style="color: {password1 === ''? 'black' : CheckPassword() == true ? 'green' : 'red'}">{checkPassword}</span>
            </li>
            <li>
                <span style="color: {password1 === '' || password2 === '' ? 'black' : password1 === password2 ? 'green' : 'red'}">{equalPassword}</span>
            </li>
        </ul>
        
        
        <br><br>

        <label for="username">닉네임:</label>
        <input type="text" bind:value={username} id="username" name="username" required>
        <br>

        <label for="email">이메일:</label>
        <input type="email" bind:value={email} id="email" name="email" required>

        <button type="submit">회원가입</button>
    </fieldset>
</form>
