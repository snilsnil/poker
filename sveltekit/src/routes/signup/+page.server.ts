import { redirect } from "@sveltejs/kit";

/**
 * @param {{ request: Request }} params
 * @returns {Promise<{ token: string | null }>}
 */
export const load = async ({ request }) => {
    const cookie = request.headers.get("cookie");

    const token = cookie
        ?.split("; ")
        .find((row) => row.startsWith("token="))
        ?.split("=")[1];

    if (token) {
        // 서버로 요청을 보내서 토큰 검증
        const response = await fetch("http://localhost:8080/checkToken", {
            method: "GET",
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });

        if (response.ok) {
            throw redirect(302, "/game");
        }
    }

    return {
        token,
    };
};
