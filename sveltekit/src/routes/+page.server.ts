import { redirect } from "@sveltejs/kit";

/**
 * @param {{ cookies: Cookies }} params
 * @returns {Promise<{ token: string | null }>}
 */
export const load = async ({ cookies }) => {
    let token = cookies.get("token");

    if (token) throw redirect(302, "/game");
};
