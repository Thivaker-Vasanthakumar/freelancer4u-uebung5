// TODO: implement
import 'dotenv/config';

const API_BASE_URL = process.env.API_BASE_URL;

export async function load({ fetch }) {
	const response = await fetch(`${API_BASE_URL}/api/company`);

	let companies = [];
	if (response.ok) {
		companies = await response.json();
	}

	return {
		companies
	};
}

export const actions = {
	default: async ({ request, fetch }) => {
		const formData = await request.formData();

		const name = formData.get('name');
		const email = formData.get('email');

		const response = await fetch(`${API_BASE_URL}/api/company`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				name,
				email
			})
		});

		if (!response.ok) {
			return {
				success: false,
				message: 'Company konnte nicht erstellt werden.'
			};
		}

		return {
			success: true,
			message: 'Company erfolgreich erstellt.'
		};
	}
};