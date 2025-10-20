package jg.model;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductDetails {
	
	private String url;
	private String name;
	private String quantity;
	private String ean;
	private String reference;
	
	
	
	public ProductDetails(String url, String name, String quantity, String ean, String reference) {
		super();
		this.url = url;
		this.name = name;
		this.quantity = quantity;
		this.ean = ean;
		this.reference = reference;
	}
	
	


	public String getUrl() {
		return url;
	}




	public void setUrl(String url) {
		this.url = url;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getQuantity() {
		return quantity;
	}




	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}




	public String getEan() {
		return ean;
	}




	public void setEan(String ean) {
		this.ean = ean;
	}




	public String getReference() {
		return reference;
	}




	public void setReference(String reference) {
		this.reference = reference;
	}




	//String q = &quot;quantity&quot;\\s*:\\s*(\\d+)
	public static void main(String[] args) {
		//"(ean13|quantity|reference)&quot;\\s*:\\s*&quot?([\\dA-Za-z]+)&quot?"
		System.out.println(getProductDetails(test, ",&quot;quantity&quot;:([0-9]+),"));
		System.out.println(getProductDetails(test, ",&quot;ean13&quot;:&quot;(\\d+)&quot;,"));
		System.out.println(getProductDetails(test, ",&quot;attributes&quot;:[^}]*?&quot;reference&quot;:&quot;([^&quot;]*)&quot;"));
	}
	

	private static String getProductDetails(String productDetails,String regex){
		Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(productDetails);
        return matcher.find() ? matcher.group(1):"";
	}
	
	
	static String test = "&quot;reference&quot;:&quot;\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "{\r\n"
			+ "    \"product_prices\": \"  <div class=\\\"product-prices js-product-prices\\\">\\n    \\n          \\n\\n    \\n      <div\\n        class=\\\"product-price h5 \\\">\\n\\n        <div class=\\\"current-price\\\">\\n          <span class='current-price-value' content=\\\"76\\\">\\n                                      76,00\\u00a0z\\u0142\\n                        <span class=\\\"current-price-value-tax\\\">\\n                              Brutto\\n                        <\\/span>\\n          <\\/span>\\n\\n                  <\\/div>\\n\\n              <\\/div>\\n    \\n\\n\\n    \\n          \\n\\n    \\n            \\n\\n    <div class=\\\"omni-price\\\">\\n  Najni\\u017csza cena z ostatnich 30 dni to: 76,00\\u00a0z\\u0142\\n    <button class=\\\"omni-open-chart\\\" title=\\\"Poka\\u017c wykresy\\\">\\n    <svg xmlns=\\\"http:\\/\\/www.w3.org\\/2000\\/svg\\\" height=\\\"1em\\\" viewBox=\\\"0 0 512 512\\\" fill=\\\"currentColor\\\">\\n      <path\\n        d=\\\"M496 384H64V80c0-8.84-7.16-16-16-16H16C7.16 64 0 71.16 0 80v336c0 17.67 14.33 32 32 32h464c8.84 0 16-7.16 16-16v-32c0-8.84-7.16-16-16-16zM464 96H345.94c-21.38 0-32.09 25.85-16.97 40.97l32.4 32.4L288 242.75l-73.37-73.37c-12.5-12.5-32.76-12.5-45.25 0l-68.69 68.69c-6.25 6.25-6.25 16.38 0 22.63l22.62 22.62c6.25 6.25 16.38 6.25 22.63 0L192 237.25l73.37 73.37c12.5 12.5 32.76 12.5 45.25 0l96-96 32.4 32.4c15.12 15.12 40.97 4.41 40.97-16.97V112c.01-8.84-7.15-16-15.99-16z\\\" \\/>\\n    <\\/svg>\\n  <\\/button>\\n  <\\/div>\\n\\n\\n<div class=\\\"omni-chart-box\\\">\\n  <canvas id=\\\"omni-chart\\\"><\\/canvas>\\n<\\/div>\\n\\n\\n<script>\\n  const omniBox = document.querySelector('.omni-chart-box');\\n  const omniBtn = document.querySelector('.omni-open-chart');\\n\\n  omniBtn.addEventListener('click', function () {\\n    omniBox.classList.toggle('open');\\n  });\\n\\n\\n\\n  const omni_chart = document.getElementById('omni-chart');\\n  new Chart(omni_chart, {\\n    type: 'line',\\n    data: {\\n    labels: ['2025-09-24','2025-09-25','2025-09-26','2025-09-27','2025-09-28','2025-09-29','2025-09-30','2025-10-01','2025-10-02','2025-10-03','2025-10-04','2025-10-05','2025-10-06','2025-10-07','2025-10-08','2025-10-09','2025-10-10','2025-10-11','2025-10-12','2025-10-13','2025-10-14','2025-10-15','2025-10-16','2025-10-17','2025-10-18','2025-10-19',],\\n    datasets: [{\\n      label: 'Cena',\\n            data: [75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,75.999978,],\\n            borderWidth: 2,\\n      borderColor: '#FF2044',\\n      backgroundColor: '#FF2044',\\n      fill: false,\\n    }]\\n    },\\n    options: {\\n      scales: {\\n        y: {\\n          beginAtZero: true\\n        }\\n      }\\n    }\\n  });\\n<\\/script>\\n\\n\\n\\n      <\\/div>\\n\",\r\n"
			+ "    \"product_cover_thumbnails\": \"<div class=\\\"images-container js-images-container\\\">\\n  <div class=\\\"images-grid\\\">\\n\\n    <picture class=\\\"images-cover\\\">\\n      <a href=\\\"https:\\/\\/jgsportesklep.pl\\/7653-large_default\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg\\\" data-fslightbox>\\n                  \\n          \\n          \\n          <img class=\\\"js-product-cover img-fluid\\\"             src=\\\"https:\\/\\/jgsportesklep.pl\\/7653-large_default\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg\\\"              alt=\\\"MOTOREX Olej Top Speed 4T 10W\\/40 JASO MA2\\\"  loading=\\\"lazy\\\"\\n            width=\\\"800\\\"\\n            height=\\\"800\\\">\\n                        <\\/a>\\n    <\\/picture>\\n\\n    <div class=\\\"images-thumbcontainer\\\">\\n      <div class=\\\"images-thumbs\\\">\\n                  <div class=\\\"thumb-item\\\">\\n            <a class=\\\"thumb\\\" href=\\\"https:\\/\\/jgsportesklep.pl\\/7653-large_default\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg\\\" data-fslightbox>\\n              <img class=\\\"thumb-image\\\"\\n                src=\\\"https:\\/\\/jgsportesklep.pl\\/7653-large_default\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg\\\"\\n                                alt=\\\"MOTOREX Olej Top Speed 4T 10W\\/40 JASO MA2\\\"\\n                                loading=\\\"lazy\\\"\\n                width=\\\"800\\\"\\n                height=\\\"800\\\">\\n            <\\/a>\\n          <\\/div>\\n              <\\/div>\\n    <\\/div>\\n\\n  <\\/div>\\n  \\n<\\/div>\\n\",\r\n"
			+ "    \"product_customization\": \"<section class=\\\"product-customization js-product-customization\\\">\\n      <div class=\\\"card card-block\\\">\\n      <p class=\\\"h4 card-title\\\">Dostosowywanie produktu<\\/p>\\n      Nie zapomnij zapisa\\u0107 swojej personalizacji, aby m\\u00f3c doda\\u0107 j\\u0105 do koszyka\\n\\n      \\n        <form method=\\\"post\\\" action=\\\"https:\\/\\/jgsportesklep.pl\\/motorex\\/1556-4345-motorex-olej-top-speed-4t-10w-40-jaso-ma2#\\/91-pojemnosc-1_l\\\" enctype=\\\"multipart\\/form-data\\\">\\n          <ul class=\\\"clearfix\\\">\\n                      <\\/ul>\\n          <div class=\\\"clearfix\\\">\\n            <button class=\\\"btn btn-primary float-xs-right\\\" type=\\\"submit\\\" name=\\\"submitCustomizedData\\\">Zapisz dostosowywanie<\\/button>\\n          <\\/div>\\n        <\\/form>\\n      \\n\\n    <\\/div>\\n  <\\/section>\\n\",\r\n"
			+ "    \"product_details\": \"<div class=\\\"js-product-details tab-pane\\\" id=\\\"product-details\\\"\\n  data-product=\\\"{&quot;id_shop_default&quot;:&quot;1&quot;,&quot;id_manufacturer&quot;:&quot;0&quot;,&quot;id_supplier&quot;:&quot;0&quot;,&quot;reference&quot;:&quot;TOP SPEED 4T 10W\\\\\\/40&quot;,&quot;is_virtual&quot;:&quot;0&quot;,&quot;delivery_in_stock&quot;:null,&quot;delivery_out_stock&quot;:null,&quot;id_category_default&quot;:&quot;11&quot;,&quot;on_sale&quot;:&quot;0&quot;,&quot;online_only&quot;:&quot;0&quot;,&quot;ecotax&quot;:0,&quot;minimal_quantity&quot;:&quot;1&quot;,&quot;low_stock_threshold&quot;:null,&quot;low_stock_alert&quot;:&quot;0&quot;,&quot;price&quot;:&quot;76,00\\\\u00a0z\\\\u0142&quot;,&quot;unity&quot;:&quot;szt&quot;,&quot;unit_price&quot;:&quot;0,00\\\\u00a0z\\\\u0142&quot;,&quot;unit_price_ratio&quot;:0,&quot;additional_shipping_cost&quot;:&quot;0.000000&quot;,&quot;customizable&quot;:&quot;0&quot;,&quot;text_fields&quot;:&quot;0&quot;,&quot;uploadable_files&quot;:&quot;0&quot;,&quot;active&quot;:&quot;1&quot;,&quot;redirect_type&quot;:&quot;301-category&quot;,&quot;id_type_redirected&quot;:&quot;0&quot;,&quot;available_for_order&quot;:&quot;1&quot;,&quot;available_date&quot;:&quot;0001-01-01&quot;,&quot;show_condition&quot;:&quot;1&quot;,&quot;condition&quot;:&quot;new&quot;,&quot;show_price&quot;:&quot;1&quot;,&quot;indexed&quot;:&quot;1&quot;,&quot;visibility&quot;:&quot;both&quot;,&quot;cache_default_attribute&quot;:&quot;0&quot;,&quot;advanced_stock_management&quot;:&quot;0&quot;,&quot;date_add&quot;:&quot;2025-09-19 11:04:48&quot;,&quot;date_upd&quot;:&quot;2025-10-17 12:10:19&quot;,&quot;pack_stock_type&quot;:&quot;3&quot;,&quot;meta_description&quot;:&quot;&quot;,&quot;meta_keywords&quot;:&quot;&quot;,&quot;meta_title&quot;:&quot;&quot;,&quot;link_rewrite&quot;:&quot;motorex-olej-top-speed-4t-10w-40-jaso-ma2&quot;,&quot;name&quot;:&quot;MOTOREX Olej Top Speed 4T 10W\\\\\\/40 JASO MA2&quot;,&quot;description&quot;:&quot;MOTOREX Olej Top Speed 4T SAE 10W\\\\\\/40 JASO MA2&quot;,&quot;description_short&quot;:&quot;MOTOREX Olej Top Speed 4T SAE 10W\\\\\\/40 JASO MA2&quot;,&quot;available_now&quot;:&quot;&quot;,&quot;available_later&quot;:&quot;&quot;,&quot;id&quot;:1556,&quot;id_product&quot;:1556,&quot;out_of_stock&quot;:2,&quot;new&quot;:1,&quot;id_product_attribute&quot;:4345,&quot;quantity_wanted&quot;:1,&quot;extraContent&quot;:[],&quot;allow_oosp&quot;:0,&quot;category&quot;:&quot;motorex&quot;,&quot;category_name&quot;:&quot;MOTOREX&quot;,&quot;link&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/motorex\\\\\\/1556-motorex-olej-top-speed-4t-10w-40-jaso-ma2&quot;,&quot;manufacturer_name&quot;:null,&quot;attribute_price&quot;:61.788600000000002410160959698259830474853515625,&quot;price_tax_exc&quot;:61.788600000000002410160959698259830474853515625,&quot;price_without_reduction&quot;:75.99997799999999870124156586825847625732421875,&quot;reduction&quot;:0,&quot;specific_prices&quot;:[],&quot;quantity&quot;:54,&quot;quantity_all_versions&quot;:0,&quot;id_image&quot;:&quot;pl-default&quot;,&quot;features&quot;:[],&quot;attachments&quot;:[],&quot;virtual&quot;:0,&quot;pack&quot;:0,&quot;packItems&quot;:[],&quot;nopackprice&quot;:0,&quot;customization_required&quot;:false,&quot;attributes&quot;:{&quot;5&quot;:{&quot;id_attribute&quot;:&quot;91&quot;,&quot;id_attribute_group&quot;:&quot;5&quot;,&quot;name&quot;:&quot;1 L&quot;,&quot;group&quot;:&quot;POJEMNO\\\\u015a\\\\u0106&quot;,&quot;public_group&quot;:&quot;POJEMNO\\\\u015a\\\\u0106&quot;,&quot;reference&quot;:&quot;308271&quot;,&quot;ean13&quot;:&quot;7611197116516&quot;,&quot;isbn&quot;:&quot;&quot;,&quot;upc&quot;:&quot;&quot;,&quot;mpn&quot;:&quot;&quot;,&quot;available_now&quot;:null,&quot;available_later&quot;:null}},&quot;rate&quot;:23,&quot;tax_name&quot;:&quot;PTU PL 23%&quot;,&quot;ecotax_rate&quot;:0,&quot;customizations&quot;:{&quot;fields&quot;:[]},&quot;id_customization&quot;:0,&quot;is_customizable&quot;:false,&quot;show_quantities&quot;:false,&quot;quantity_label&quot;:&quot;Przedmiot&quot;,&quot;quantity_discounts&quot;:[],&quot;customer_group_discount&quot;:0,&quot;images&quot;:[{&quot;cover&quot;:&quot;1&quot;,&quot;id_image&quot;:&quot;7653&quot;,&quot;legend&quot;:&quot;MOTOREX Olej Top Speed 4T 10W\\\\\\/40 JASO MA2&quot;,&quot;position&quot;:&quot;1&quot;,&quot;bySize&quot;:{&quot;small_default&quot;:{&quot;url&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-small_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;,&quot;width&quot;:98,&quot;height&quot;:98,&quot;sources&quot;:{&quot;jpg&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-small_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;}},&quot;cart_default&quot;:{&quot;url&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-cart_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;,&quot;width&quot;:125,&quot;height&quot;:125,&quot;sources&quot;:{&quot;jpg&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-cart_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;}},&quot;home_default&quot;:{&quot;url&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-home_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;,&quot;width&quot;:450,&quot;height&quot;:450,&quot;sources&quot;:{&quot;jpg&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-home_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;}},&quot;medium_default&quot;:{&quot;url&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-medium_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;,&quot;width&quot;:452,&quot;height&quot;:452,&quot;sources&quot;:{&quot;jpg&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-medium_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;}},&quot;large_default&quot;:{&quot;url&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-large_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;,&quot;width&quot;:800,&quot;height&quot;:800,&quot;sources&quot;:{&quot;jpg&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-large_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;}}},&quot;small&quot;:{&quot;url&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-small_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;,&quot;width&quot;:98,&quot;height&quot;:98,&quot;sources&quot;:{&quot;jpg&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-small_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;}},&quot;medium&quot;:{&quot;url&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-home_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;,&quot;width&quot;:450,&quot;height&quot;:450,&quot;sources&quot;:{&quot;jpg&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-home_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;}},&quot;large&quot;:{&quot;url&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-large_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;,&quot;width&quot;:800,&quot;height&quot;:800,&quot;sources&quot;:{&quot;jpg&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-large_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;}},&quot;associatedVariants&quot;:[]}],&quot;cover&quot;:{&quot;cover&quot;:&quot;1&quot;,&quot;id_image&quot;:&quot;7653&quot;,&quot;legend&quot;:&quot;MOTOREX Olej Top Speed 4T 10W\\\\\\/40 JASO MA2&quot;,&quot;position&quot;:&quot;1&quot;,&quot;bySize&quot;:{&quot;small_default&quot;:{&quot;url&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-small_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;,&quot;width&quot;:98,&quot;height&quot;:98,&quot;sources&quot;:{&quot;jpg&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-small_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;}},&quot;cart_default&quot;:{&quot;url&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-cart_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;,&quot;width&quot;:125,&quot;height&quot;:125,&quot;sources&quot;:{&quot;jpg&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-cart_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;}},&quot;home_default&quot;:{&quot;url&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-home_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;,&quot;width&quot;:450,&quot;height&quot;:450,&quot;sources&quot;:{&quot;jpg&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-home_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;}},&quot;medium_default&quot;:{&quot;url&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-medium_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;,&quot;width&quot;:452,&quot;height&quot;:452,&quot;sources&quot;:{&quot;jpg&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-medium_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;}},&quot;large_default&quot;:{&quot;url&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-large_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;,&quot;width&quot;:800,&quot;height&quot;:800,&quot;sources&quot;:{&quot;jpg&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-large_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;}}},&quot;small&quot;:{&quot;url&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-small_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;,&quot;width&quot;:98,&quot;height&quot;:98,&quot;sources&quot;:{&quot;jpg&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-small_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;}},&quot;medium&quot;:{&quot;url&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-home_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;,&quot;width&quot;:450,&quot;height&quot;:450,&quot;sources&quot;:{&quot;jpg&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-home_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;}},&quot;large&quot;:{&quot;url&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-large_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;,&quot;width&quot;:800,&quot;height&quot;:800,&quot;sources&quot;:{&quot;jpg&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-large_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;}},&quot;associatedVariants&quot;:[]},&quot;has_discount&quot;:false,&quot;discount_type&quot;:null,&quot;discount_percentage&quot;:null,&quot;discount_percentage_absolute&quot;:null,&quot;discount_amount&quot;:null,&quot;discount_amount_to_display&quot;:null,&quot;price_amount&quot;:76,&quot;unit_price_full&quot;:&quot;0,00\\\\u00a0z\\\\u0142 szt&quot;,&quot;show_availability&quot;:true,&quot;availability_message&quot;:&quot;DOST\\\\u0118PNY&quot;,&quot;availability_date&quot;:null,&quot;availability&quot;:&quot;available&quot;}\\\">\\n  <h4 class=\\\"tab-pane-title\\\"><span class=\\\"tab-pane-title-text\\\">Dane techniczne<\\/span><\\/h4>\\n\\n  \\n      \\n<\\/div>\",\r\n"
			+ "    \"product_variants\": \"<div class=\\\"product-variants js-product-variants\\\">\\n          <div class=\\\"clearfix product-variants-item\\\">\\n      <span class=\\\"control-label\\\">POJEMNO\\u015a\\u0106: \\n                      1 L                                                            <\\/span>\\n              <select\\n          class=\\\"form-control form-control-select\\\"\\n          id=\\\"group_5\\\"\\n          aria-label=\\\"POJEMNO\\u015a\\u0106\\\"\\n          data-product-attribute=\\\"5\\\"\\n          name=\\\"group[5]\\\">\\n                      <option value=\\\"91\\\" title=\\\"1 L\\\" selected=\\\"selected\\\">1 L<\\/option>\\n                      <option value=\\\"92\\\" title=\\\"4 L\\\">4 L<\\/option>\\n                      <option value=\\\"93\\\" title=\\\"20 L\\\">20 L<\\/option>\\n                  <\\/select>\\n          <\\/div>\\n      <\\/div>\\n\",\r\n"
			+ "    \"product_discounts\": \"<section class=\\\"product-discounts js-product-discounts\\\">\\n  <\\/section>\\n\",\r\n"
			+ "    \"product_add_to_cart\": \"<div class=\\\"product-add-to-cart js-product-add-to-cart\\\">\\n  \\n\\n    \\n      <div class=\\\"product-quantity clearfix\\\">\\n\\n          <span class=\\\"product-quantity-label\\\">Ilo\\u015b\\u0107<\\/span>\\n\\n          <div class=\\\"product-quantity-input\\\">\\n            <div class=\\\"quantity-minus\\\">\\n             -\\n            <\\/div>\\n\\n            <input type=\\\"number\\\" name=\\\"qty\\\"  inputmode=\\\"numeric\\\" pattern=\\\"[0-9]*\\\"\\n               value=\\\"1\\\" min=\\\"1\\\"\\n               class=\\\"input-group\\\" aria-label=\\\"Ilo\\u015b\\u0107\\\">\\n\\n\\n            <div class=\\\"quantity-plus\\\">\\n              +\\n            <\\/div>\\n          <\\/div>\\n\\n      <\\/div>\\n\\n      <div class=\\\"add\\\">\\n        <button class=\\\"btn btn-primary add-to-cart\\\" data-button-action=\\\"add-to-cart\\\" type=\\\"submit\\\" role=\\\"button\\\" aria-label=\\\"Dodaj do koszyka\\\" title=\\\"Dodaj do koszyka\\\" >\\n          <div class=\\\"btn-icon\\\" aria-hidden=\\\"true\\\">\\n            <svg xmlns=\\\"http:\\/\\/www.w3.org\\/2000\\/svg\\\" viewBox=\\\"0 0 576 512\\\" fill=\\\"currentColor\\\">\\n              <path\\n                d=\\\"M0 24C0 10.7 10.7 0 24 0L69.5 0c22 0 41.5 12.8 50.6 32l411 0c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3l-288.5 0 5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5L488 336c13.3 0 24 10.7 24 24s-10.7 24-24 24l-288.3 0c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5L24 48C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96z\\\" \\/>\\n            <\\/svg>\\n          <\\/div>\\n          Dodaj do koszyka\\n        <\\/button>\\n\\n\\n              <\\/div>\\n\\n      \\n        <div class=\\\"product-additional-info js-product-additional-info\\\">\\n  \\n<\\/div>\\n      \\n\\n      \\n\\n    \\n\\n    \\n    \\n      <div class=\\\"product-minimal-quantity js-product-minimal-quantity\\\">\\n              <\\/div>\\n    \\n  <\\/div>\\n\",\r\n"
			+ "    \"product_additional_info\": \"<div class=\\\"product-additional-info js-product-additional-info\\\">\\n  \\n<\\/div>\\n\",\r\n"
			+ "    \"product_images_modal\": \"<div class=\\\"modal fade js-product-images-modal\\\" id=\\\"product-modal\\\">\\n  <div class=\\\"modal-dialog\\\" role=\\\"document\\\">\\n    <div class=\\\"modal-content\\\">\\n      <div class=\\\"modal-body\\\">\\n                <figure>\\n                      <picture>\\n                                          <img\\n                class=\\\"js-modal-product-cover product-cover-modal\\\"\\n                width=\\\"800\\\"\\n                src=\\\"https:\\/\\/jgsportesklep.pl\\/7653-large_default\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg\\\"\\n                                  alt=\\\"MOTOREX Olej Top Speed 4T 10W\\/40 JASO MA2\\\"\\n                  title=\\\"MOTOREX Olej Top Speed 4T 10W\\/40 JASO MA2\\\"\\n                                height=\\\"800\\\"\\n              >\\n            <\\/picture>\\n                    <figcaption class=\\\"image-caption\\\">\\n          \\n            <div id=\\\"product-description-short\\\">MOTOREX Olej Top Speed 4T SAE 10W\\/40 JASO MA2<\\/div>\\n          \\n        <\\/figcaption>\\n        <\\/figure>\\n        <aside id=\\\"thumbnails\\\" class=\\\"thumbnails js-thumbnails text-sm-center\\\">\\n          \\n            <div class=\\\"js-modal-mask mask  nomargin \\\">\\n              <ul class=\\\"product-images js-modal-product-images\\\">\\n                                  <li class=\\\"thumb-container js-thumb-container\\\">\\n                    <picture>\\n                                                                  <img\\n                        data-image-large-src=\\\"https:\\/\\/jgsportesklep.pl\\/7653-large_default\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg\\\"\\n                        data-image-large-sources=\\\"{&quot;jpg&quot;:&quot;https:\\\\\\/\\\\\\/jgsportesklep.pl\\\\\\/7653-large_default\\\\\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg&quot;}\\\"                        class=\\\"thumb js-modal-thumb\\\"\\n                        src=\\\"https:\\/\\/jgsportesklep.pl\\/7653-home_default\\/motorex-olej-top-speed-4t-10w-40-jaso-ma2.jpg\\\"\\n                                                  alt=\\\"MOTOREX Olej Top Speed 4T 10W\\/40 JASO MA2\\\"\\n                          title=\\\"MOTOREX Olej Top Speed 4T 10W\\/40 JASO MA2\\\"\\n                                                width=\\\"450\\\"\\n                        height=\\\"148\\\"\\n                      >\\n                    <\\/picture>\\n                  <\\/li>\\n                              <\\/ul>\\n            <\\/div>\\n          \\n                  <\\/aside>\\n      <\\/div>\\n    <\\/div><!-- \\/.modal-content -->\\n  <\\/div><!-- \\/.modal-dialog -->\\n<\\/div><!-- \\/.modal -->\\n\",\r\n"
			+ "    \"product_flags\": \"\\n    <ul class=\\\"product-flags js-product-flags\\\">\\n                    <li class=\\\"product-flag new\\\">Nowy<\\/li>\\n            <\\/ul>\\n\\n\",\r\n"
			+ "    \"product_url\": \"https:\\/\\/jgsportesklep.pl\\/motorex\\/1556-4345-motorex-olej-top-speed-4t-10w-40-jaso-ma2#\\/91-pojemnosc-1_l\",\r\n"
			+ "    \"product_minimal_quantity\": \"1\",\r\n"
			+ "    \"product_has_combinations\": true,\r\n"
			+ "    \"id_product_attribute\": 4345,\r\n"
			+ "    \"id_customization\": 0,\r\n"
			+ "    \"product_title\": \"MOTOREX Olej Top Speed 4T 10W\\/40 JASO MA2\",\r\n"
			+ "    \"is_quick_view\": false\r\n"
			+ "}";
	
	
	
	
	


}
