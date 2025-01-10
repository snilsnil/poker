<script>
  import { goto } from '$app/navigation';

  function logout() {
    document.cookie = 'token=; Max-Age=0; path=/';
    goto('/');
  }
</script>

<h1>여기는 게임대기방</h1>
<button on:click={logout}>로그아웃</button>