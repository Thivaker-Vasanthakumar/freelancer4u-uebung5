<script>
	export let data;
	export let form;
</script>

<h1>Create Companies</h1>

{#if form?.success}
	<div style="background: #d4edda; color: #155724; padding: 10px; margin-bottom: 15px; border-radius: 4px;">
		{form.message}
	</div>
{/if}

{#if form && form.success === false}
	<div style="background: #f8d7da; color: #721c24; padding: 10px; margin-bottom: 15px; border-radius: 4px;">
		{form.message}
	</div>
{/if}

<form method="POST" style="margin-bottom: 30px;">
	<div style="margin-bottom: 10px;">
		<label for="name">Name</label><br />
		<input id="name" name="name" type="text" required style="width: 300px;" />
	</div>

	<div style="margin-bottom: 10px;">
		<label for="email">Email</label><br />
		<input id="email" name="email" type="email" required style="width: 300px;" />
	</div>

	<button type="submit">Submit</button>
</form>

<h1>All Companies</h1>

<table border="1" cellpadding="8" cellspacing="0" style="border-collapse: collapse; width: 100%;">
	<thead>
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>ID</th>
		</tr>
	</thead>
	<tbody>
		{#each data.companies as company}
			<tr>
				<td>{company.name}</td>
				<td>{company.email}</td>
				<td>{company.id}</td>
			</tr>
		{:else}
			<tr>
				<td colspan="3">No companies found.</td>
			</tr>
		{/each}
	</tbody>
</table>