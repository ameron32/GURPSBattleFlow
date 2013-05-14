
package com.ameron32.gurpsbattleflow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ameron32.gurpsbattleflow.Importer.ImportType;

public class MainActivity extends Activity {

    /**
     * High Tech is failing due to dropbox. Changing the name makes it work
     * fine.
     */

    private TextView tvStepName, tvStepDescription;
    private ImageView ivStepImage;
    private LinearLayout llRefs, llSplitTo, llGoTo;

    private List<Step> listOfSteps;
    private Step currentStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        tvStepName = (TextView) findViewById(R.id.tvStepName);
        tvStepDescription = (TextView) findViewById(R.id.tvStepDescription);
        ivStepImage = (ImageView) findViewById(R.id.ivStepImage);
        ivStepImage.setAdjustViewBounds(true);
        llRefs = (LinearLayout) findViewById(R.id.llRefs);
        llSplitTo = (LinearLayout) findViewById(R.id.llSplitTo);
        llGoTo = (LinearLayout) findViewById(R.id.llGoTo);
    }

    private void prepare() {
        download(false);
        load();
    }

    private void download(boolean update) {
        String[] fromLocations = new String[References.references.length];
        String[] toFileNames = new String[References.references.length];
        for (short i = 0; i < References.references.length; i++) {
            fromLocations[i] = References.references[i][References.DOWNLOAD_LOCATION];
            toFileNames[i] = References.references[i][References.FILENAME_LOCAL];
        }
        downloadAssets(null, toFileNames, update, fromLocations);
    }

    private void load() {
        Importer ii = new Importer();
        loadSteps(ii);
    }

    private void activateStep(String stepName) {
        currentStep = getStep(stepName);
        updateFromStep();
    }

    private SharedPreferences saveStep;

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor = saveStep.edit();
        editor.putString("Step", currentStep.getStepName());
        editor.commit();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        prepare();
    }

    public void proceed() {
        saveStep = getSharedPreferences("Step", 0);
        activateStep(saveStep.getString("Step", "Pre1"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public static boolean isComplete = false;

    private void downloadAssets(String dlDir, String[] fileNames,
            boolean update, String[] sUrl) {
        final ProgressDialog mDownloadDialog = new ProgressDialog(MainActivity.this);
        mDownloadDialog.setTitle("Downloading from Dropbox...");
        mDownloadDialog.setMessage("");
        mDownloadDialog.setIndeterminate(false);
        mDownloadDialog.setMax(100);
        mDownloadDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mDownloadDialog.setCancelable(false);

        // execute this when the downloader must be fired
        final Downloader d = new Downloader(MainActivity.this, mDownloadDialog, b);
        if (dlDir != null)
            d.setDlDir(dlDir);
        d.setDlFiles(fileNames);
        if (update)
            d.setUpdate(update);
        d.execute(sUrl);
    }

    @SuppressWarnings("unchecked")
    private void loadSteps(Importer importer) {
        // suppressed warning, list type not defined
        listOfSteps = importer.readCSVIntoList(Downloader.getDlDir()
                + References.getFileNameByInitials("x"), new ArrayList<Step>(),
                ImportType.FlowChart);
    }

    private Step getStep(String stepName) {
        for (Step s : listOfSteps) {
            if (s.getStepName().equals(stepName))
                return s;
        }
        return null;
    }

    int columns;

    private void updateFromStep() {
        // populate text from data
        tvStepName.setText(currentStep.getStepName());
        tvStepDescription.setText(currentStep.getDescription());
        ivStepImage.setImageResource(MainActivity.this.getResources()
                .getIdentifier(currentStep.getTable(), "drawable",
                        "com.ameron32.gurpsbattleflow"));

        // empty views before re-populating
        llRefs.removeAllViews();
        llSplitTo.removeAllViews();
        llGoTo.removeAllViews();

        // populate buttons from data
        columns = 4;
        buildLinks(currentStep.getRefs(), llRefs, LinkType.Ref);
        buildLinks(currentStep.getSplitToOpts(), llSplitTo, LinkType.SplitTo);
        buildLinks(currentStep.getGoToOpts(), llGoTo, LinkType.GoTo);
    }

    LinearLayout bHolder;

    private void buildLinks(List<String> sourceList, LinearLayout destLayout,
            LinkType lt) {
        bHolder = new LinearLayout(MainActivity.this);
        // generate buttons for destLayout
        short count = 0;
        for (String s : sourceList) {
            // if this is the fifth button, add pack up the row,
            // throw it in the layout, and generate a new row
            if (count % columns == 0) {
                if (count != 0) {
                    destLayout.addView(bHolder);
                    bHolder = new LinearLayout(MainActivity.this);
                }
            }
            bHolder.addView(generateLinkButton(s, lt));
            count++;
        }
        if (bHolder.getChildCount() != 0) {
            destLayout.addView(bHolder);
        }
    }

    private int dP(float f) {
        return Math.round(f * MainActivity.this.getResources()
                //
                .getDisplayMetrics().density);
    }

    private int getScreenWidth() {
        return MainActivity.this.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public boolean onSearchRequested() {
        final AlertDialog.Builder selectStep = new AlertDialog.Builder(
                MainActivity.this);
        selectStep.setTitle("Jump to...");
        selectStep.setMessage("Choose a step to jump immediately to:");

        final LinearLayout verticalLayout = new LinearLayout(MainActivity.this);
        verticalLayout.setOrientation(LinearLayout.VERTICAL);
        final EditText input = new EditText(MainActivity.this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        final RadioGroup preOrPostOrNone = new RadioGroup(MainActivity.this);
        final RadioButton pre = new RadioButton(MainActivity.this);
        final RadioButton post = new RadioButton(MainActivity.this);
        final RadioButton none = new RadioButton(MainActivity.this);

        pre.setText("Pre-Combat");
        none.setText("In-Combat");
        post.setText("Post-Combat");

        preOrPostOrNone.addView(pre);
        preOrPostOrNone.addView(none);
        preOrPostOrNone.addView(post);
        preOrPostOrNone.check(none.getId());

        verticalLayout.addView(preOrPostOrNone);
        verticalLayout.addView(input);

        selectStep.setView(verticalLayout);

        selectStep.setPositiveButton("Jump",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (pre.isChecked())
                            activateStep("Pre" + input.getText().toString());
                        else if (post.isChecked())
                            activateStep("Post" + input.getText().toString());
                        else
                            activateStep(input.getText().toString());
                    }
                });

        final AlertDialog dialog = selectStep.create();

        dialog.show();

        return false;
    }

    Button b;

    private Button generateLinkButton(String link, LinkType lt) {
        float textSize = (12f * ((float) (((double) getScreenWidth()) / 480.0)));
        b = new Button(MainActivity.this);
        b.setTextSize(textSize);
        b.setWidth((getScreenWidth() - dP(40.0f)) / columns);
        switch (lt) {
            case GoTo:
                b.setText(">" + link);
                b.setTag(tagGoTo, link);
                b.setOnClickListener(oclGoTo);
                break;
            case SplitTo:
                b.setText("S>" + link);
                b.setTag(tagSplitTo, link);
                b.setOnClickListener(oclSplitTo);
                break;
            case Ref:
                b.setText(link);
                b.setTag(tagRef, link);
                b.setOnClickListener(oclRef);
                break;
        }
        return b;
    }

    private enum LinkType {
        GoTo, SplitTo, Ref
    }

    private static final int tagGoTo = 94949494;
    OnClickListener oclGoTo = new OnClickListener() {
        @Override
        public void onClick(View v) {
            activateStep((String) v.getTag(tagGoTo));
        }
    };
    private static final int tagSplitTo = 95959595;
    OnClickListener oclSplitTo = new OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    private static final int tagRef = 96969696;
    OnClickListener oclRef = new OnClickListener() {
        @Override
        public void onClick(View v) {
            loadReference((String) v.getTag(tagRef));
        }
    };

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.i_jump_to:
                onSearchRequested();
                break;
            case R.id.i_back_to_start:
                activateStep("Pre1");
                break;
            case R.id.i_end_combat:
                activateStep("Post1");
                break;
            case R.id.i_finish:
                finish();
                break;
            case R.id.i_redownload:
                download(true);
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    private void loadReference(String ref) {
        String[] refBookPage = interpretRef(ref);
        openInPDF(refBookPage);
    }

    private String[] interpretRef(String ref) {
        if (ref.substring(0, 1).equals("B")) {
            Log.e("Info", ref.substring(0, 1) + "|" + ref.substring(1));
            return new String[] {
                    "B", ref.substring(1)
            };
        } else {
            Log.e("Info", ref.substring(0, 2) + "|" + ref.substring(2));
            return new String[] {
                    ref.substring(0, 2), ref.substring(2)
            };
        }
    }

    private void openInPDF(String[] refBookPage) {
        int pageNumber = 0;
        int pageOffset = References.getOffsetByInitials(refBookPage[0]);

        File file = new File(Downloader.getDlDir()
                + References.getFileNameByInitials(refBookPage[0]));
        Log.e("", Downloader.getDlDir()
                + References.getFileNameByInitials(refBookPage[0]));
        pageNumber = Integer.parseInt(refBookPage[1]) + pageOffset;

        if (file.exists()) {
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(path, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("page", pageNumber);

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(MainActivity.this,
                        "No Application Available to View PDF",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
